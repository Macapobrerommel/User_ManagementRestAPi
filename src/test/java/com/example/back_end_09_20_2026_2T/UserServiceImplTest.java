package com.example.back_end_09_20_2026_2T;


import com.example.back_end_09_20_2026_2T.DTO.CreateUserRequest;
import com.example.back_end_09_20_2026_2T.DTO.UpdateUserRequest;
import com.example.back_end_09_20_2026_2T.Entity.User;
import com.example.back_end_09_20_2026_2T.Exception.UserNotFoundException;
import com.example.back_end_09_20_2026_2T.Repository.UserRepository;
import com.example.back_end_09_20_2026_2T.Service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void findById_whenUserDoesNotExist_throwUserNotFoundException(){
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(99L));
    }
    @Test
    void findById_whenUserExist(){
        //Arrange
        User existing = new User();
        existing.setFirstName("Rommel");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));


        //Act
        User user = userService.findById(1L);


        //Asser
        assertEquals("Rommel", user.getFirstName());
    }

    @Test
    void updateUser_whenUSerExists_updatesField(){
        User existing = new User();
        existing.setFirstName("old");
        when(userRepository.findById(99L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UpdateUserRequest userRequest = new UpdateUserRequest("New","Name","new@email.com");
        User savedUser = userService.updateUser(99L,userRequest);


        assertEquals("New",savedUser.getFirstName());
    }

    @Test
    void deleteUser_whenUserExists_updatesField(){
        User existing = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));

        userService.deleteUser(1L);

        verify(userRepository).delete(existing);
    }
    @Test
    void saveUser_whenUserExists_savesField(){

        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        CreateUserRequest userRequest = new CreateUserRequest("New","Name", "new@email.com");
        User newUser = userService.saveUser(userRequest);

        assertEquals("New", newUser.getFirstName());
        assertEquals("Name", newUser.getLastName());
        assertEquals("new@email.com", newUser.getEmail());

    }
    @Test
    void getAllUser(){
        User user1 = new User("New","Name","new@Email.com");
        User user2 = new User("New","Name","new@Email.com");
        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> userList = userService.getAllUsers();

        assertEquals(2, userList.size());
        assertEquals(user1, userList.get(0));
        assertEquals(user2, userList.get(1));
        verify(userRepository).findAll();
    }
}
