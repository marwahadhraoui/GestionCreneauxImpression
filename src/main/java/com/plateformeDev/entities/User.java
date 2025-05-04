package com.plateformeDev.entities;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String   nom, prenom, email, mdp; 
    
   
    private String role;
    
   
    
    // Ajoutez cette méthode pour vérifier le mot de passe
    public boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.mdp);
    } 
    
    public void setEncryptedPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
        }
        this.mdp = passwordEncoder.encode(rawPassword);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
    }

    public User(String role, String nom, String prenom, String email, String mdp) {
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }
}