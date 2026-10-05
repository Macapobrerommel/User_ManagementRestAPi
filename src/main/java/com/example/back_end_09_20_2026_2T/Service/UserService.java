package com.example.back_end_09_20_2026_2T.Service;

import com.example.back_end_09_20_2026_2T.DTO.CreateUserRequest;
import com.example.back_end_09_20_2026_2T.DTO.UpdateUserRequest;
import com.example.back_end_09_20_2026_2T.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(CreateUserRequest request);
    public List<User> getAllUsers();
    public User findById(Long id);
    public User updateUser(Long id, UpdateUserRequest updateUserRequest);
    public void deleteUser(Long id);
}
