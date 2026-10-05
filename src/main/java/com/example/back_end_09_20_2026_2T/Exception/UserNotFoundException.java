package com.example.back_end_09_20_2026_2T.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String name){
        super(name);
    }
}
