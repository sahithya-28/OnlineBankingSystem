package com.OBS.onlinebankingsystem.dao;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.OBS.onlinebankingsystem.model.User;
import com.OBS.onlinebankingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
