package com.springboot.indeedclone.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private  String type;

    @Column
    private String company;

    @Column
    private String experience;

    @Column(length = 1000)
    private String description;

    @Column
    private List<String> technology;

    @Column
    private String salary;

    @Column
    private String location;

    @Column
    @UpdateTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIncludeProperties({"id", "name"})
    private User user;

    public Job(String title, String type, String company, String experience, String description, List<String> technology, String salary, String location) {
        this.title = title;
        this.type = type;
        this.company = company;
        this.experience = experience;
        this.description = description;
        this.technology = technology;
        this.salary = salary;
        this.location = location;
    }

    public Job(int id) {
        this.id = id;
    }

}
