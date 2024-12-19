package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<Message> createMessage(Message message) {
        Optional<Account> postedAccount = accountRepository.findById(message.getPostedBy());
        if (message.getMessageText().length() == 0 || 
            message.getMessageText().length() >= 255 || 
            postedAccount.isPresent() == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(messageRepository.save(message));
        }
    }

    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageRepository.findAll());     
    }

}
