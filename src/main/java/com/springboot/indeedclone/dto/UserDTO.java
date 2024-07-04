package com.springboot.indeedclone.dto;

import com.springboot.indeedclone.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private Set<Role> roles;
}
