package com.springboot.indeedclone.controller;

import com.springboot.indeedclone.dto.UserDTO;
import com.springboot.indeedclone.response.ApiResponse;
import com.springboot.indeedclone.response.Responses;
import com.springboot.indeedclone.service.UserService;
import com.springboot.indeedclone.listObjectResponse.UserListResponse;
import com.springboot.indeedclone.repository.UserRepository;
import com.springboot.indeedclone.objectResponse.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<UserListResponse> getAllUsers(){
        UserListResponse userListResponse = new UserListResponse();
        List<UserDTO> users = this.userService.getAllUsers();
        userListResponse.set(users);
        return new ResponseEntity<>(userListResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable int id){
        UserDTO user = this.userService.getUserById(id);
        if(user == null){
            return Responses.notFound("user");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.set(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

}
