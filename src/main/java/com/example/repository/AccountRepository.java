package com.example.repository;
import java.util.Optional;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

// @Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    // Optional <Account> findByUsername(String username);
    Optional <Account> findByUsernameAndPassword(String username, String password);
}

