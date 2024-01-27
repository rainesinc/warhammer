package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/new")
    public String showNewForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        var roleList = roleRepository.findAll();
        model.addAttribute("roleList", roleList);
        return "user_new_form";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) throws NotFoundException {
        ModelAndView mav = new ModelAndView("user_edit_form.html");
        User user = userService.findUserById(id);
        mav.addObject("user", user);
        var roleList = roleRepository.findAll(); // todo show selected for already assigned roles
        mav.addObject("roleList", roleList);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user)
            throws BadRequestException, NoSuchAlgorithmException {
        userService.createUser(user);
        return "redirect:/users";
    }

    @RequestMapping
    public String getUsers(Model model){
        var userList =
                StreamSupport
                        .stream(userService.findAllUsers().spliterator(),false).toList();
        model.addAttribute("userList", userList);
        return "users";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.removeUserById(id);
        return "redirect:/users";
    }

}
