package com.connetz.connetz.controllers;

import com.connetz.connetz.models.Chat;
import com.connetz.connetz.models.User;
import com.connetz.connetz.models.messages.Messages;
import com.connetz.connetz.services.MessageServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.messaging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


// Same Message controlls that we have in class
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageServices messagesService;

    public MessageController(MessageServices messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("chats")
    // Check the passing path to a name its not wrapped in the paramater of the function to be called
    public ResponseEntity<ApiResponseFormat<List<Chat>>> getMessagesChats(@PathVariable String userId)  {
        try {
            List<Chat> chats = messagesService.getMessageChats(userId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Success", chats, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseFormat(true, "Unable to get chats.", null, e));
        }
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<List<Chat>>> getMessageById(@PathVariable String userId) {

        try{
            Messages user = messagesService.getMessageById(userId);

            if (user != null)
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User Found", user, null));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null, null));

        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<String>> updateMessage(
            @PathVariable String id, @RequestBody Map<String, Object> updateFields) {
        try {
            WriteResult result = messagesService.updateMessages(id, updateFields);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Message updated successfully", "Update time: " + result.getUpdateTime(), null));
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling of InterruptedException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating message", null, e.getMessage()));
        }
    }*/

}
