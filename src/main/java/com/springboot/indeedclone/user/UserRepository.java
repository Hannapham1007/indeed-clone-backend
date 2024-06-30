package com.springboot.indeedclone.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User findUserById(Integer id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
