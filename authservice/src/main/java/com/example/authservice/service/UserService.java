package com.example.authservice.service;

import com.example.authservice.exception.SuchObjectExistsException;
import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authservice.model.Role;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> findUserById(Integer id) {
        return repository.findById(id);
    }

    public void saveUser(String username, String password, Role role) {
        if (repository.findByUsername(username).isPresent())
            throw new SuchObjectExistsException("User with this username already exists");
        var user = User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
        repository.save(user);
    }
}