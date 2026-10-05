package com.example.back_end_09_20_2026_2T.Repository;


import com.example.back_end_09_20_2026_2T.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
