package com.plateformeDev.controllers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.plateformeDev.Exception.ExamenImportException;
import com.plateformeDev.dto.ExamenDTO;
import com.plateformeDev.entities.Enseignant;
import com.plateformeDev.entities.Examen;
import com.plateformeDev.entities.ExamenImportError;
import com.plateformeDev.entities.ExamenImportResult;
import com.plateformeDev.entities.User;
import com.plateformeDev.service.ExamenService;
import com.plateformeDev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/examens")
@CrossOrigin(origins = "*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @Autowired
    private UserService userRepository;

    @GetMapping("/alertes")
    public ResponseEntity<List<Examen>> getExamensSansReservation() {
        List<Examen> examens = examenService.getAlertesExamensSansReservation();
        return ResponseEntity.ok(examens);
    }

    @GetMapping
    public ResponseEntity<List<Examen>> getExamens() {
        List<Examen> examens = examenService.getExamens();
        return ResponseEntity.ok(examens);
    }

    @PostMapping("/import")
    public ResponseEntity<ExamenImportResult> importExamens(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new ExamenImportResult(
                    Collections.emptyList(),
                    List.of(new ExamenImportError(0, "Le fichier est vide", ""))
            ));
        }

        List<Examen> validExamens = new ArrayList<>();
        List<ExamenImportError> errors = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            if (file.getOriginalFilename().endsWith(".csv")) {
                parseCsvFile(is, validExamens, errors);
            } else {
                return ResponseEntity.badRequest().body(new ExamenImportResult(
                        Collections.emptyList(),
                        List.of(new ExamenImportError(0, "Format de fichier non supporté", ""))
                ));
            }

            return ResponseEntity.ok(new ExamenImportResult(validExamens, errors));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ExamenImportResult(
                    Collections.emptyList(),
                    List.of(new ExamenImportError(0, "Erreur de traitement: " + e.getMessage(), ""))
            ));
        }
    }

    private void parseCsvFile(InputStream is, List<Examen> validExamens, List<ExamenImportError> errors) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            String[] nextLine;
            int lineNumber = 0;

            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                if (lineNumber == 1) continue; // Sauter l'en-tête

                try {
                    Examen examen = validateAndCreateExamen(nextLine, lineNumber);
                    validExamens.add(examen);
                } catch (ExamenImportException e) {
                    errors.add(new ExamenImportError(
                            lineNumber,
                            e.getMessage(),
                            String.join(",", nextLine)
                    ));
                }
            }
        }
    }

    private Examen validateAndCreateExamen(String[] data, int lineNumber) throws ExamenImportException {
        if (data.length < 5) {
            throw new ExamenImportException("Nombre de colonnes insuffisant");
        }

        String matiere = data[0].trim();
        String dateStr = data[1].trim();
        String niveau = data[2].trim();
        String specialite = data[3].trim();
        String emailEnseignant = data[4].trim();

        if (matiere.isEmpty() || dateStr.isEmpty() || niveau.isEmpty() || emailEnseignant.isEmpty()) {
            throw new ExamenImportException("Champs obligatoires manquants");
        }

        LocalDate dateExamen;
        try {
            dateExamen = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new ExamenImportException("Format de date invalide (YYYY-MM-DD attendu)");
        }

        User enseignant = userRepository.findByEmail(emailEnseignant);
        if (enseignant == null) {
            throw new ExamenImportException("Enseignant non trouvé");
        }

        Examen examen = new Examen();
        examen.setMatiere(matiere);
        examen.setDateExamen(dateExamen);
        examen.setNiveau(niveau);
        examen.setSpecialite(specialite);
        examen.setEnseignant((Enseignant) enseignant);

        return examen;
    }

    @PostMapping("/confirm-import")
    public ResponseEntity<Void> confirmImport(@RequestBody List<ExamenDTO> examensDto) {
        if (examensDto == null || examensDto.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<Examen> examens = examensDto.stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());

            examenService.saveAll(examens);
            // notificationService.scheduleExamNotifications(examens); // Ligne commentée

            return ResponseEntity.ok().build();

        }  catch (Exception e) {
            // Gérer d'autres types d'exceptions si nécessaire
            // log.error("Erreur inattendue lors de la confirmation d'import", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private Examen convertToEntity(ExamenDTO dto)  {
        Examen examen = new Examen();
        examen.setMatiere(dto.getMatiere());
        examen.setDateExamen(dto.getDateExamen());
        examen.setNiveau(dto.getNiveau());
        examen.setSpecialite(dto.getSpecialite());
        User enseignant = userRepository.findByEmail(dto.getEnseignantEmail());
        if (enseignant == null) {
        	examen.setEnseignant(null);
        }
        else
        {
        	examen.setEnseignant((Enseignant) enseignant);
        }

        

        return examen;
    }

   
}