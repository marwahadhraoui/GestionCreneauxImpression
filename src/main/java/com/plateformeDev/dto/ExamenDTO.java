package com.plateformeDev.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamenDTO {
    private String matiere;
    private LocalDate dateExamen;
    private String niveau;
    private String specialite;
    private String enseignantEmail;
}