package com.example.controller;

import java.util.Optional;
import com.example.entity.Account;
import com.example.entity.Message;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.exception.InvalidMessageLengthException;
import com.example.exception.InvalidRegistrationException;
import com.example.exception.InvalidUserException;
import com.example.exception.UserAlreadyExistsException;

@RestController
public class SocialMediaController {
    
    private AccountService accountService;
    private MessageService messageService;


    @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        return accountService.registerAccount(account);
    }
    
    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        return accountService.loginAccount(account);
    }

}