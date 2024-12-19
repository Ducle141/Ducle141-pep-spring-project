package com.example.controller;

import java.util.Optional;
import com.example.entity.Account;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountService;

    // @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Account toRet = accountService.registerUser(account);
        return ResponseEntity.status(HttpStatus.OK).body(toRet);
        // try {
        //     Account newAccount = accountService.registerAccount(account.getUsername(), account.getPassword());
        //     System.out.print("---------");
        //     System.out.println(newAccount);
        //     return ResponseEntity.ok(newAccount);
        // } catch (IllegalArgumentException e) {
        //     if (e.getMessage().contains("Username already exists")) {
        //         return ResponseEntity.status(409).build();
        //     }
        //     return ResponseEntity.status(400).build();
        // }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws AuthenticationException{
        String username = account.getUsername();
        String password = account.getPassword();
        Optional<Account> retrievedUsername = accountService.loginAccount(username, password);
        if (retrievedUsername.isPresent()) {
            if (retrievedUsername.getPassword().equals(password)) {
                return ResponseEntity.ok(retrievedUsername);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            
        }
    }
}

