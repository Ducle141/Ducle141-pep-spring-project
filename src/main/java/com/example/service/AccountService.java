package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.*;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository ar) {
        this.accountRepository = ar;
    }

    public Account registerUser(Account account) throws InvalidRegistrationException, UserAlreadyExistsException
    {
        String username = account.getUsername();
        String password = account.getPassword();
        if(username == "" || password.length() < 4) {
            throw new InvalidRegistrationException();
        } else if(accountRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException();
        } else {
            return accountRepository.save(account);
        }
    }

    public Account login(Account account) throws AuthenticationException
    {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword())
            .orElseThrow(() -> new AuthenticationException(null));
    }
    
    
}