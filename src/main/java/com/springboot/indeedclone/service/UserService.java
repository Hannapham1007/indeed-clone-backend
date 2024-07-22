package com.springboot.indeedclone.service;

import com.springboot.indeedclone.dto.UserDTO;
import com.springboot.indeedclone.model.User;
import com.springboot.indeedclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<UserDTO> getAllUsers(){
        return this.userRepository.findAll().stream().map(this::convertDataToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return convertDataToDTO(userOptional.get());
        }
        return null;
    }

    private UserDTO convertDataToDTO(User userData) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userData.getId());
        userDTO.setUsername(userData.getUsername());
        userDTO.setEmail(userData.getEmail());
        userDTO.setRoles(userData.getRoles());
        return userDTO;
    }
}
