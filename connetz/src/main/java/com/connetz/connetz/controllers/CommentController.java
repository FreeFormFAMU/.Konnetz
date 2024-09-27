package com.connetz.connetz.controllers;

import com.connetz.connetz.models.comments.Comments;
import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.services.CommentServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.connetz.connetz.util.ErrorMessage;
import com.connetz.connetz.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private final CommentServices commentService;

    @Value("${response.status}")
    private int statusCode;
    @Value("${response.name")
    private String name;
    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "CommentService";

    public CommentController(CommentServices commentService){this.commentService = commentService; payload = null;}

    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<List<Comments>>> getAllComments() {
        try {
            payload = commentService.getAllComments();
            statusCode = 200;
            name = "comments";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch comments from database", CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();

    }

    @GetMapping("/{id}") // postid need to convert to a string
    public ResponseEntity<Map<String, Object>> getCommentById(@PathVariable(name = "id") String id) {
        try {
            payload = commentService.getCommentbyId(id);
            statusCode = 200;
            name = "comment";


        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch comment from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody(required = false) Comments comment) {
        try {
            payload = commentService.createComment(comment);
            statusCode = 201;
            name = "commentId";

        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot create comment", CLASS_NAME, e.toString());

        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();

    }


}
