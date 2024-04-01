package com.connetz.connetz.controllers;

import com.connetz.connetz.models.comments.Comments;
import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.services.CommentServices;
import com.connetz.connetz.util.ApiResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private final CommentServices commentService;

    public CommentController(CommentServices commentService){this.commentService = commentService;}

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Comments>>> getAllComments() {
        try {
            List<Comments> commentsList = commentService.getAllComments();

            if (commentsList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No posts found", null, null));

            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Comments recieved", commentsList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving Comments", null, e.getMessage()));
        }
    }

}
