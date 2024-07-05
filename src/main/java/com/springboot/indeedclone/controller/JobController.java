package com.springboot.indeedclone.controller;

import com.springboot.indeedclone.dto.JobDTO;
import com.springboot.indeedclone.listObjectResponse.JobListResponse;
import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.objectResponse.JobResponse;
import com.springboot.indeedclone.response.ApiResponse;
import com.springboot.indeedclone.response.Responses;
import com.springboot.indeedclone.service.JobService;
import com.springboot.indeedclone.utils.ValidationUtils;
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
    private JobService jobService;

    @GetMapping
    public ResponseEntity<JobListResponse> getAllJobs(){
        JobListResponse jobListResponse = new JobListResponse();
        List<JobDTO> jobs = this.jobService.getAllJobs();
        jobListResponse.set(jobs);
        return new ResponseEntity<>(jobListResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createAJobPost(@RequestBody Job job){
        if(ValidationUtils.isInValidJob(job)){
            return Responses.badRequest("create", job.getClass().getSimpleName());
        }
        JobDTO createJob = this.jobService.createJobPost(job);
        JobResponse jobResponse = new JobResponse();
        jobResponse.set(createJob);
        return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateJobPost(@PathVariable int id, @RequestBody Job job){
        if(ValidationUtils.isInValidJob(job)){
            return Responses.badRequest("update", job.getClass().getSimpleName());
        }
        JobDTO jobPostToUpdate = this.jobService.updateJobPost(id, job);
        JobResponse jobResponse = new JobResponse();
        jobResponse.set(jobPostToUpdate);
        return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAJobPost(@PathVariable int id){
        JobDTO jobPostToDelete = this.jobService.deleteJobPost(id);
        JobResponse jobResponse = new JobResponse();
        jobResponse.set(jobPostToDelete);
        return ResponseEntity.ok(jobResponse);
    }

    @GetMapping("list/belong/{id}")
    public ResponseEntity<ApiResponse<?>> getJobPostListBelongToUser(@PathVariable int id){
        JobListResponse jobListResponse = new JobListResponse();
        List<JobDTO> jobs = this.jobService.getJobListByUser(id);
        jobListResponse.set(jobs);
        return new ResponseEntity<>(jobListResponse, HttpStatus.OK);
    }
}
