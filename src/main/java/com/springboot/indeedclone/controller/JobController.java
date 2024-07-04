package com.springboot.indeedclone.controller;

import com.springboot.indeedclone.listObjectResponse.JobListResponse;
import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.model.User;
import com.springboot.indeedclone.objectResponse.JobResponse;
import com.springboot.indeedclone.repository.JobRepository;
import com.springboot.indeedclone.repository.UserRepository;
import com.springboot.indeedclone.response.ApiResponse;
import com.springboot.indeedclone.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job_posts")
@CrossOrigin
public class JobController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;


    @GetMapping
    public ResponseEntity<JobListResponse> getAllJobs(){

        JobListResponse jobListResponse = new JobListResponse();
        List<Job> jobs = this.jobRepository.findAll();
        jobListResponse.set(jobs);
        return new ResponseEntity<>(jobListResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createAJobPost(@RequestBody Job job){
        int userId = job.getUser().getId();
        User user = this.userRepository.findUserById(userId);
        if(user == null){
            return Responses.notFound("user");
        }
        Job createJob = this.jobRepository.save(job);
        createJob.setUser(user);
        JobResponse jobResponse = new JobResponse();
        jobResponse.set(createJob);
        return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateJobPost(@PathVariable int id, @RequestBody Job job){
        Job jobPostToUpdate = this.jobRepository.findById(id).orElse(null);
        if (jobPostToUpdate == null){
            return Responses.notFound("job post");
        }
        jobPostToUpdate.setTitle(job.getTitle());
        jobPostToUpdate.setType(job.getType());
        jobPostToUpdate.setCompany(job.getCompany());
        jobPostToUpdate.setExperience(job.getExperience());
        jobPostToUpdate.setDescription(job.getDescription());
        jobPostToUpdate.setTechnology(job.getTechnology());
        jobPostToUpdate.setSalary(job.getSalary());
        jobPostToUpdate.setLocation(job.getLocation());
        jobPostToUpdate.setUser(job.getUser());

        this.jobRepository.save(jobPostToUpdate);

        JobResponse jobResponse = new JobResponse();
        jobResponse.set(jobPostToUpdate);
        return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAJobPost(@PathVariable int id){
        Job jobPostToDelete = this.jobRepository.findById(id).orElse(null);
        if(jobPostToDelete == null){
            return Responses.notFound("job post");
        }
        this.jobRepository.delete(jobPostToDelete);
        JobResponse jobResponse = new JobResponse();
        jobResponse.set(jobPostToDelete);
        return ResponseEntity.ok(jobResponse);
    }

    @GetMapping("list/belong/{id}")
    public ResponseEntity<ApiResponse<?>> getJobPostListBelongToUser(@PathVariable int id){
        User user = this.userRepository.findUserById(id);
        if(user == null){
            return Responses.notFound("user");
        }
        JobListResponse jobListResponse = new JobListResponse();
        List<Job> jobs = this.jobRepository.findByUser(user);
        jobListResponse.set(jobs);
        return new ResponseEntity<>(jobListResponse, HttpStatus.OK);
    }
}
