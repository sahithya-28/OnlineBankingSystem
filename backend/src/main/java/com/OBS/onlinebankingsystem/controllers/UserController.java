package com.OBS.onlinebankingsystem.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import com.OBS.onlinebankingsystem.model.User;
import com.OBS.onlinebankingsystem.dao.UserDao;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userDao.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userDao.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}