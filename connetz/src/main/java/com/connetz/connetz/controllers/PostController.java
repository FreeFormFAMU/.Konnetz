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

    @GetMapping("/")
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

    @GetMapping("/{id}") // postid need to convert to a string
    public ResponseEntity<ApiResponseFormat<Post>> getPostById(@PathVariable(name = "id") String id) {
        try {
            Post post = postServices.getPostById(id);

            if (post != null)
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User Found", post, null));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null, null));

        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseFormat<String>> createPost(@RequestBody(required = false) Post post) {
        try {
            if (post == null) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponseFormat<>(false, "Request body is missing.", null, null));
            }

            String id = postServices.createPost(post);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Post was successfully created.", id, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating post.", null, e));

        }



    }

    @DeleteMapping("remove/{id}") // check
    public ResponseEntity<ApiResponseFormat<WriteResult>> deletePost(@PathVariable(name="id") String postId ){
        try{
            WriteResult result = postServices.removePost(postId);
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Job deleted", result, null));
        } catch(ExecutionException | InterruptedException e ) {
            return ResponseEntity.status(404)
                    .body(new ApiResponseFormat<>(false, "Unable to delete task", null, e));
        }
    }

    @PutMapping(path="/{id}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes = Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<WriteResult>> updatePath(@PathVariable("id") String id, @RequestBody Map<String, Object> updateValues)
    {
        //Two different types of ways to pass a value in one method

        //Get id of the user from url
        try {
            WriteResult result = postServices.updatePost(id, updateValues);//function throws Exeception to get caught in this class to limit the try catches

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseFormat<>(false, "User successfully updated", result, null));
        }catch(ExecutionException | InterruptedException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating user", null, e));

        }


    }



}




