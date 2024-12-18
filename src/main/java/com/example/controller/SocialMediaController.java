package com.example.controller;

import java.util.Optional;
import com.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        try {
            Account newAccount = accountService.registerAccount(account.getUsername(), account.getPassword());
            return ResponseEntity.ok(newAccount);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Username already exists")) {
                return ResponseEntity.status(409).build();
            }
            return ResponseEntity.status(400).build();
        }
    }
}

