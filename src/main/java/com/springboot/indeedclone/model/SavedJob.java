package com.springboot.indeedclone.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "savedJobs")
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIncludeProperties({"id", "name"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIncludeProperties({"id", "title"})
    private Job job;

}
