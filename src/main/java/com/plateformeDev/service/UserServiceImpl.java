package com.plateformeDev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plateformeDev.entities.User;
import com.plateformeDev.entities.Enseignant;
import com.plateformeDev.entities.Secretaire;
import com.plateformeDev.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User saveUser(User u) { 
    	 if (u.getMdp() == null || u.getMdp().isEmpty()) {
    	        throw new IllegalArgumentException("Le mot de passe est requis");
    	    }
        // Crypter le mot de passe avant sauvegarde
        u.setEncryptedPassword(u.getMdp(), passwordEncoder);
        
        // Convertir selon le rôle
        if (u.getRole() != null) {
            switch (u.getRole().toUpperCase()) {
                case "ENSEIGNANT":
                    Enseignant enseignant = new Enseignant();
                    copyUserProperties(u, enseignant);
                    return userRepo.save(enseignant);
                case "SECRETAIRE":
                    Secretaire secretaire = new Secretaire();
                    copyUserProperties(u, secretaire);
                    return userRepo.save(secretaire);
                default:
                    // Par défaut, on crée un Enseignant
                    Enseignant defaultUser = new Enseignant();
                    copyUserProperties(u, defaultUser);
                    return userRepo.save(defaultUser);
            }
        }
        // Si aucun rôle n'est spécifié, créer un Enseignant par défaut
        Enseignant defaultUser = new Enseignant();
        copyUserProperties(u, defaultUser);
        return userRepo.save(defaultUser);
    }
    
    private void copyUserProperties(User source, User target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setEmail(source.getEmail());
        target.setMdp(source.getMdp()); // Le mot de passe est déjà crypté
        target.setRole(source.getRole());
    }

    @Override
    public User updateUser(User u) {
        User existingUser = userRepo.findById(u.getId()).orElse(null);
        if (existingUser != null) {
            // Conserver le type existant (Enseignant/Secretaire)
            if (u.getMdp() != null && !u.getMdp().isEmpty()) {
                existingUser.setEncryptedPassword(u.getMdp(), passwordEncoder);
            }
            existingUser.setNom(u.getNom());
            existingUser.setPrenom(u.getPrenom());
            existingUser.setEmail(u.getEmail());
            existingUser.setRole(u.getRole());
            
            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(User u) {
        userRepo.delete(u);
    }

    @Override
    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getSecretaires() {
        return userRepo.findByRole("SECRETAIRE");
    }
    
    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.checkPassword(password, passwordEncoder)) {
            return user;
        }
        return null;
    }
    
    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null ) {
            return user;
        }
        return null;
    }
}