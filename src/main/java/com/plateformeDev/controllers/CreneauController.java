package com.plateformeDev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.entities.User;
import com.plateformeDev.service.CreneauxService;
import com.plateformeDev.service.UserService;

@RestController
@RequestMapping("/creneaux")
@CrossOrigin(origins = "*") 
public class CreneauController {

    @Autowired
    private CreneauxService creneauxService; 
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Creneaux> getAllCreneaux() {
        return creneauxService.getAllCreneaux();
    }

    @GetMapping("/{id}")
    public Creneaux getCreneauById(@PathVariable("id") int id) {
        return creneauxService.getCreneau(id);
    }
    
    @PostMapping
    public Creneaux addCreneau(@RequestBody Creneaux creneaux) {
        return creneauxService.saveCreneau(creneaux);
    }

    @PutMapping("/{id}")
    public Creneaux updateCreneau(@PathVariable("id") int id, @RequestBody Creneaux creneaux) {
        creneaux.setId(id); 
        return creneauxService.updateCreneau(creneaux);
    }

    @DeleteMapping("/{id}")
    public void deleteCreneau(@PathVariable("id") int id) {
        creneauxService.deleteCreneauxById(id);
    } 
    
    @GetMapping("/secretaire/{userId}")
	public List<Creneaux> getCreneauxBySecretaire(@PathVariable int userId) {
		User secretaire = userService.getUser(userId); // Récupérer l'utilisateur par son ID
		if (secretaire != null ) {
			return creneauxService.getCreneauxByUser(secretaire);
		} else {
			// Gérer le cas où l'utilisateur n'est pas trouvé ou n'est pas un secrétaire
			throw new RuntimeException("Secrétaire non trouvé ou ID utilisateur invalide.");
		}
	}
}