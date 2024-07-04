package com.springboot.indeedclone.repository;

import com.springboot.indeedclone.model.ERole;
import com.springboot.indeedclone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
