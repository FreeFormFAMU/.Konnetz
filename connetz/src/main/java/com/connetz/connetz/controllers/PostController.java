package com.connetz.connetz.controllers;

import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.services.PostServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.connetz.connetz.util.ResponseWrapper;
import com.connetz.connetz.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.glassfish.jersey.internal.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.connetz.connetz.util.ErrorMessage;

@RestController
@RequestMapping("api/posts")
public class PostController {


    private final PostServices postServices;


    @Value("${response.status}")
    private int statusCode;
    @Value("${response.name")
    private String name;
    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "PostService";

    public PostController(PostServices postServices) {
        this.postServices = postServices;
        payload = null;
    }

    @GetMapping("/") // Good
    public ResponseEntity<Map<String, Object>> getAllPosts() {
        try {
            payload = postServices.getAllPost();
            statusCode = 200;
            name = "posts";


        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch posts from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();

    }

    @GetMapping("/{id}") // Good
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable(name = "id") String id) {
        try {
            payload = postServices.getPostById(id);
            statusCode = 200;
            name = "post";


        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch post from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody(required = false) Post post) {
        try {
            payload = postServices.createPost(post);
            statusCode = 201;
            name = "postId";

        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot create post", CLASS_NAME, e.toString());

        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();

    }

    @DeleteMapping("/remove/{id}") // check
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable(name = "id") String postId) {
        try {
            postServices.removePost(postId);
            statusCode = 204;
            name = "message";
            payload = "Delete successful for post with id " + postId;
        } catch (Exception e) {
            payload = new ErrorMessage("Cannot delete post with id " + postId, CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }


    @PutMapping(path = "/{id}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes = Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<Map<String, Object>> updatePath(@PathVariable("id") String id, @RequestBody Map<String, Object> updateValues) {
        //Two different types of ways to pass a value in one method

        //Get id of the user from url
        try {
            postServices.updatePost(id, updateValues);
            statusCode = 201;
            name = "message";
            payload = "Update successful for post with id " + id;

        } catch (Exception e) {
            payload = new ErrorMessage("Cannot update post with id " + id, CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();


    }

    // Get followers by Id
    @GetMapping("users/{userId}") // trouble
    public ResponseEntity<ApiResponseFormat<List<Post>>> getPostsByUser(@PathVariable(name = "userId") String userId) {
        try {
            payload = postServices.getPostsByUser(userId);
            statusCode = 200;
            name = "posts";


        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch posts from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }


    @GetMapping("/c/{slug}")
    public ResponseEntity<ApiResponseFormat<List<Post>>> getPostByCategory(@PathVariable(name = "slug") String slug) {
        try {
            payload = postServices.getPostsByCategory(slug);
            statusCode = 200;
            name = "posts";


        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch posts from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }
}


