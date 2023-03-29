package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.Message;
import com.example.demo.backend.repository.MessageRepository;
import com.example.demo.backend.service.MessageService;

public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        messageRepository.save(message);
        return message;
    }
}