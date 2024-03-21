package com.connetz.connetz.controllers;
import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.services.PostServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.connetz.connetz.util.Utility;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


// Create post will be in here, like_post will be in here,
// The save_post will be in here and the comments post will be in here
//
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private final PostServices postServices;

    public PostController(PostServices postServices, PostServices postServices1) {
        this.postServices = postServices;}

    // Get all post getAllPost
    @GetMapping("/") // not found
    public ResponseEntity<ApiResponseFormat<List<Post>>>getAllPost() {
        try{
            List<Post> postList = postServices.getAllPost();

            if(postList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "Post not found", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true,"Post receviced correct", postList, null));
        } catch ( ExecutionException |InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    //Get post by the Id getPostById
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


    //Post to be able to create the post createPost
    @PostMapping("create") // check
    public ResponseEntity<ApiResponseFormat<String>> createPost(@RequestBody(required = false) Post post){
        if (post == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseFormat<>(false, "Request body is missing.", null, null));
        }
        try{
            String id = postServices.createPost(post);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Post was successfully created.", id, null));
        }
        catch(ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating post.", null, e));

        }
    }


    //Remove a post removePost
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


    //Put to be able to Update the post
    // check
    @PutMapping(path="/{id}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes = Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<WriteResult>> updateUser(@PathVariable("id") String id, @RequestBody Map<String, Object> updateValues)
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



