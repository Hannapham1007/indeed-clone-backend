package com.springboot.indeedclone.repository;

import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByUser(User user);

}
