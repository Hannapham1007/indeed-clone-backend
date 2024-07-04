package com.springboot.indeedclone.service;

import com.springboot.indeedclone.dto.JobDTO;
import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.model.User;
import com.springboot.indeedclone.repository.JobRepository;
import com.springboot.indeedclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public List<JobDTO> getAllJobs() {
        return this.jobRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JobDTO> getJobListByUser(int userId) {
        User user = getUserById(userId);
        return this.jobRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO createJobPost(int userId, Job job) {
        User user = getUserById(userId);
        job.setUser(user);
        Job savedJob = this.jobRepository.save(job);
        return convertToDTO(savedJob);
    }

    public JobDTO updateJobPost(int jobId, Job jobDetails) {
        Job jobToUpdate = this.jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        jobToUpdate.setTitle(jobDetails.getTitle());
        jobToUpdate.setType(jobDetails.getType());
        jobToUpdate.setCompany(jobDetails.getCompany());
        jobToUpdate.setExperience(jobDetails.getExperience());
        jobToUpdate.setDescription(jobDetails.getDescription());
        jobToUpdate.setTechnology(jobDetails.getTechnology());
        jobToUpdate.setSalary(jobDetails.getSalary());
        jobToUpdate.setLocation(jobDetails.getLocation());
        Job updatedJob = this.jobRepository.save(jobToUpdate);
        return convertToDTO(updatedJob);
    }

    public JobDTO deleteJobPost(int jobId) {
        Job job = this.jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));
        JobDTO deleteJobDTO = convertToDTO(job);
        this.jobRepository.delete(job);
        return deleteJobDTO;

    }

    private User getUserById(int userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    private JobDTO convertToDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setCompany(job.getCompany());
        jobDTO.setExperience(job.getExperience());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setSalary(job.getSalary());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setType(job.getType());
        jobDTO.setTechnology(job.getTechnology());
        return jobDTO;
    }


}
