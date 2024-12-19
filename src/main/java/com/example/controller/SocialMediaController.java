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
import com.example.exception.InvalidRegistrationException;
import com.example.exception.UserAlreadyExistsException;

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

    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleUserAlreadyExists(UserAlreadyExistsException e)
    {
        return e.getMessage();
    }
}

