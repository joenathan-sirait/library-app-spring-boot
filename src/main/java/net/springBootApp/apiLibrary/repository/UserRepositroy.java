package net.springBootApp.apiLibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootApp.apiLibrary.model.entity.User;

@Repository
public interface UserRepositroy extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  
    

}
