package com.example.back_end_09_20_2026_2T.Service;

import com.example.back_end_09_20_2026_2T.DTO.CreateUserRequest;
import com.example.back_end_09_20_2026_2T.DTO.UpdateUserRequest;
import com.example.back_end_09_20_2026_2T.Entity.User;
import com.example.back_end_09_20_2026_2T.Exception.UserNotFoundException;
import com.example.back_end_09_20_2026_2T.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(CreateUserRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found"));
    }

    @Override
    @Transactional
    public User updateUser(Long id, UpdateUserRequest updateUserRequest) {

        User user = findById(id);
            user.setFirstName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());
            user.setEmail(updateUserRequest.getEmail());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.delete(findById(id));
    }




}
