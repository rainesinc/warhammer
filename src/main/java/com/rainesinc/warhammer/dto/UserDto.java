package com.rainesinc.warhammer.dto;

import com.rainesinc.warhammer.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
}
