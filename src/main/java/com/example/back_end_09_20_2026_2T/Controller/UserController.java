package com.example.back_end_09_20_2026_2T.Controller;

import com.example.back_end_09_20_2026_2T.DTO.CreateUserRequest;
import com.example.back_end_09_20_2026_2T.DTO.UpdateUserRequest;
import com.example.back_end_09_20_2026_2T.Entity.User;
import com.example.back_end_09_20_2026_2T.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService =userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>>getAllUsers(){

        List<User> userList = userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserRequest createUserRequest){

        User savedUser = userService.saveUser(createUserRequest);


       return  ResponseEntity
               .status(HttpStatus.CREATED)
               .body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){

        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest){
        userService.updateUser(id,updateUserRequest);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
       return ResponseEntity.notFound().build();
    }


}
