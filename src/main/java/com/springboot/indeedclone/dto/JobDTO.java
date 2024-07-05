package com.springboot.indeedclone.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class JobDTO {
    private int id;
    private String title;
    private String description;
    private String company;
    private String location;
    private String type;
    private List<String> technology;
    private String experience;
    private String salary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
