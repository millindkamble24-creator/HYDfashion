package org.example.hydbackend.auth.repository;

import org.example.hydbackend.auth.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String Email);
}
