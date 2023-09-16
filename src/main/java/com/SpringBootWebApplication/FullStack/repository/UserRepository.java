package com.SpringBootWebApplication.FullStack.repository;

import com.SpringBootWebApplication.FullStack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    User findUserById(Long userId);
}
