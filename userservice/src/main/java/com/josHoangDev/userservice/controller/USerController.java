package com.josHoangDev.userservice.controller;

import com.josHoangDev.userservice.data.User;
import com.josHoangDev.userservice.model.UserDTO;
import com.josHoangDev.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/listUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }
}
