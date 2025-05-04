package com.plateformeDev.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter 
@DiscriminatorValue("Enseignant")
public class Enseignant extends User {

    
    private String grade;

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return super.toString() + " Enseignant [grade=" + grade + "]";
    }
}
