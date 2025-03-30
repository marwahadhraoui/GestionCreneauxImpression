package com.plateformeDev.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plateformeDev.entities.User;
public interface UserRepository extends JpaRepository<User, Integer> {

	//jpa repo dispose de toutes les methodes qu'on est besoin comme save,findbyid,findall,delete,...
    List<User> findByRole(String role);

}
