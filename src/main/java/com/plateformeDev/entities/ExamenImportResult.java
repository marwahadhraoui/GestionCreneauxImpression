package com.plateformeDev.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExamenImportResult {
    private List<Examen> validExamens;
    private List<ExamenImportError> errors;
    // getters/setters
}