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
import com.plateformeDev.service.CreneauxService;

@RestController
@RequestMapping("/creneaux")
@CrossOrigin(origins = "*") 
public class CreneauController {

    @Autowired
    private CreneauxService creneauxService;

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
}