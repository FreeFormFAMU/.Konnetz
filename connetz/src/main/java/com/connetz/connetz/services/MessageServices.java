package com.connetz.connetz.services;


import com.connetz.connetz.controllers.MessageController;
import com.connetz.connetz.util.ApiResponseFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class MessageServices {
    private final MessageService messageService;

    public MessageController (MessageServices messageServices) {
        this.messageService = messageServices;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers() {
        try {

        }
    }
}
