package com.springboot.indeedclone.user;

import com.springboot.indeedclone.response.ApiResponse;
import com.springboot.indeedclone.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserListResponse> getAllUsers(){
        UserListResponse userListResponse = new UserListResponse();
        List<User> users = this.userRepository.findAll();
        userListResponse.set(users);
        return new ResponseEntity<>(userListResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable int id){
        User user = this.userRepository.findUserById(id);
        if(user == null){
            return Responses.notFound("user");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.set(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

}
