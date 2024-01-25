package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    @Autowired
    private final UserService service;

    @PostMapping // CREATE
    public User postUser(@Valid @RequestBody User user, String password)
            throws BadRequestException, NoSuchAlgorithmException {
        return service.createUser(user, password);
    }

    @GetMapping("readById/{id}") // READ by id
    public User getUserById(@PathVariable("id") int id) throws NotFoundException {
        return service.findUserById(id);
    }

    @GetMapping // READ all
    public List<User> getUsers(){
        return StreamSupport
                .stream(service.findAllUsers().spliterator(),false).toList();
    }

    @PutMapping("update") // UPDATE by user
    public void putUser(@Valid @RequestBody User user){
        service.updateUser(user);
    }

    @DeleteMapping("deleteById/{id}") // DELETE by id
    public void deleteUserById(@PathVariable("id") int id){
        service.removeUserById(id);
    }


}
