package com.plateformeDev.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;


import com.plateformeDev.entities.Examen;
import com.plateformeDev.service.ExamenService;

@RestController
@RequestMapping("/examens")
@CrossOrigin(origins = "*") 
public class ExamenController { 
	@Autowired
	private ExamenService examenService;
	
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
	


}
