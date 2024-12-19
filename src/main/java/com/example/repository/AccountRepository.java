package com.example.repository;
import java.util.Optional;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

// @Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
        // Find an Account by Username
        Optional<Account> findAccountByUsername(String username);

        // Find an Account by Username and Password
        Optional<Account> findAccountByUsernameAndPassword(String username, String password);
}

