package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import com.example.exception.InvalidMessageLengthException;
import com.example.exception.InvalidUserException;


@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messRepo, AccountService accSer) {
        this.messageRepository = messRepo;
        this.accountService = accSer;
    }

    public Message addMessage(Message messToAdd) throws InvalidUserException, InvalidMessageLengthException {
        Integer userID = messToAdd.getPostedBy();
        if(accountService.getAccountById(userID).isEmpty())
        {
            throw new InvalidUserException();
        }
        String messageText = messToAdd.getMessageText();
        if(messageText.length() == 0 || messageText.length() > 255)
        {
            throw new InvalidMessageLengthException();
        }
        return messageRepository.save(messToAdd);
    }
}
