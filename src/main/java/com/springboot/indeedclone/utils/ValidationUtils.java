package com.springboot.indeedclone.utils;

import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class ValidationUtils {
    public static boolean isInValidJob(Job job) {
        return isNullOrEmpty(job.getTitle()) ||
                isNullOrEmpty(job.getCompany()) ||
                isNullOrEmpty(job.getDescription()) ||
                isNullOrEmpty(job.getLocation()) ||
                isNullOrEmpty(job.getSalary()) ||
                isNullOrEmpty(job.getType()) ||
                job.getTechnology() == null || job.getTechnology().isEmpty();
    }

    public static boolean isInValidUser(User user) {
        return isNullOrEmpty(user.getUsername()) ||
                isNullOrEmpty(user.getEmail()) ||
                isNullOrEmpty(user.getPassword());
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static <T> T getById(int id, JpaRepository<T, Integer> repository) {
        return repository.findById(id).orElse(null);
    }
}

