package com.connetz.connetz.controllers;

import com.connetz.connetz.services.FeedServices;
import com.connetz.connetz.services.UserServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

// The feed controller will have the post the user follow has in it
@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private final FeedServices feedService;

    public FeedController(FeedServices feedService) {this.feedService = feedService;}


    @GetMapping("/{id}/like")
    public ResponseEntity<ApiResponseFormat<String>> addLike(@PathVariable String id) {
        try {
            WriteResult result = feedService.addLike(id);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Like added successfully", "Updated time: " + result.getUpdateTime(), null));
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling of InterruptedException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error adding like", null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseFormat<String>> updateFeed(
            @PathVariable String id, @RequestBody Map<String, Object> updateFields) {
        try {
            WriteResult result = feedService.updateFeed(id, updateFields);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Feed updated successfully", "Updated time: " + result.getUpdateTime(), null));
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling of InterruptedException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating feed", null, e.getMessage()));
        }
    }


}
