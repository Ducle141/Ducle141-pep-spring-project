package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.length() < 4) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        if (accountRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        Account account = new Account(username, password);
        return accountRepository.save(account);
    }

    public Account loginAccount(String username, String password) {
        System.out.print("3---" + accountRepository.findByUsername(username));
        return accountRepository.findByUsername(username);
    }
}