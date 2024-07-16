package com.springboot.indeedclone.repository;
import com.springboot.indeedclone.model.SavedJob;
import com.springboot.indeedclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJob, Integer> {
    List<SavedJob> findSavedJobByUser(User user);
}
