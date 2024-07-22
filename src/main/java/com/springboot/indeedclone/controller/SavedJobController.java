package com.springboot.indeedclone.controller;

import com.springboot.indeedclone.listObjectResponse.SavedJobListResponse;
import com.springboot.indeedclone.model.Job;
import com.springboot.indeedclone.model.SavedJob;
import com.springboot.indeedclone.model.User;
import com.springboot.indeedclone.objectResponse.SavedJobResponse;
import com.springboot.indeedclone.repository.JobRepository;
import com.springboot.indeedclone.repository.SavedJobRepository;
import com.springboot.indeedclone.repository.UserRepository;
import com.springboot.indeedclone.response.ApiResponse;
import com.springboot.indeedclone.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saved_jobs")
@CrossOrigin
public class SavedJobController {
    @Autowired
    private SavedJobRepository savedJobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public ResponseEntity<SavedJobListResponse> getAllSavedJobs(){
        SavedJobListResponse savedJobListResponse = new SavedJobListResponse();
        List<SavedJob> savedJobs = this.savedJobRepository.findAll();
        savedJobListResponse.set(savedJobs);
        return new ResponseEntity<>(savedJobListResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> getSavedJobsByUserId(@PathVariable int userId){
        User user = this.userRepository.findUserById(userId);
        if(user == null){
            return Responses.notFound("user");
        }
        SavedJobListResponse savedJobListResponse = new SavedJobListResponse();
        List<SavedJob> savedJobs = this.savedJobRepository.findSavedJobByUser(user);
        savedJobListResponse.set(savedJobs);
        return new ResponseEntity<>(savedJobListResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> savedJob(@RequestParam int userId, @RequestParam int jobId){
        User user = this.userRepository.findUserById(userId);
        Optional<Job> jobOp = this.jobRepository.findById(jobId);
        if(user == null || !jobOp.isPresent()){
            return Responses.badRequest("create", "saved job");
        }
        Job job = jobOp.get();
        SavedJob jobToSave = new SavedJob();
        jobToSave.setUser(user);
        jobToSave.setJob(job);
        this.savedJobRepository.save(jobToSave);
        SavedJobResponse savedJobResponse = new SavedJobResponse();
        savedJobResponse.set(jobToSave);
        return new ResponseEntity<>(savedJobResponse, HttpStatus.CREATED);
    }


    @DeleteMapping("/{savedJobId}")
    public ResponseEntity<ApiResponse<?>> deleteSavedJob(@PathVariable int savedJobId){
        SavedJob jobToDelete = this.savedJobRepository.findById(savedJobId).orElse(null);
        if(jobToDelete == null){
            return Responses.notFound("savedJob");
        }
        this.savedJobRepository.delete(jobToDelete);
        SavedJobResponse savedJobResponse = new SavedJobResponse();
        savedJobResponse.set(jobToDelete);
        return ResponseEntity.ok(savedJobResponse);

    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteAllSavedJobs(){
        SavedJobListResponse savedJobListResponse = new SavedJobListResponse();
        List<SavedJob> savedJobs = this.savedJobRepository.findAll();
        this.savedJobRepository.deleteAll();
        savedJobListResponse.set(savedJobs);
        return new ResponseEntity<>(savedJobListResponse, HttpStatus.OK);
    }

}
