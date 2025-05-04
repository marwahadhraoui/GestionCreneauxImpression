package com.plateformeDev.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matiere;
    private LocalDate dateExamen;
    private String niveau;
    private String specialite;

    @ManyToOne
    private Enseignant enseignant;
}
