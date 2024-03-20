package com.connetz.connetz.controllers;
import com.connetz.connetz.models.User;
import com.connetz.connetz.services.UserServices;
import com.connetz.connetz.util.ApiResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;



// 2/21/2024
// In the User control should have the follow_user, unfollow_user, and skills contorller.

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserServices userService;
    public UserController(UserServices userService) {this.userService = userService;}


    // Get the user by their id
    @GetMapping("/{id}") // check
    public ResponseEntity<ApiResponseFormat<User>> getUserByID(@PathVariable(name = "id") String id) {
        try {
            User user = userService.getUserById(id);

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

    // Get all users
    @GetMapping("/") // check
    public ResponseEntity<ApiResponseFormat<List<User>>> getAllUsers() {
        try {
            List<User> userList = userService.getAllUsers();
            if (userList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", userList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    // Get followers by Id
    @GetMapping("followers/{followers_id}") // trouble
    public ResponseEntity<ApiResponseFormat<User>> getFollowUser(@PathVariable(name="followers_id") String followersId) {
        try{
            User user = userService.getFollowUserId(followersId);

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

    // Get following by Id
    @GetMapping("following/{following_id}")  // trouble
    public ResponseEntity<ApiResponseFormat<User>> getFollowingUserId(@PathVariable(name="following_id") String followingId) {
        try{
            User user = userService.getFollowingUserId(followingId);

            if (user != null)
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Following user Found", user, null));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Following not found", null, null));

        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving following", null, e.getMessage()));
        }
    }

    @PostMapping("/") // check
    public ResponseEntity<ApiResponseFormat<String>> addUser(@RequestBody(required = false) User user){
        if (user == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseFormat<>(false, "Request body is missing.", null, null));
        }
        try{
            String id = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "User successfully created job.", id, null));
        }
        catch(ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating user.", null, e));

        }
    }

    @GetMapping("/following") // returns all the users but not specific following
    public ResponseEntity<ApiResponseFormat<List<User>>> getAllFollowing() {
        try {
            List<User> userList = userService.getAllFollowing();
            if (userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No following users found.", null, null));
            }
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Following users retrieved successfully", userList, null));
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling of InterruptedException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving following users", null, e.getMessage()));
        }
    }

    @GetMapping("/followers") // Same things gets all the followers
    public ResponseEntity<ApiResponseFormat<List<User>>> getAllFollower() {
        try {
            List<User> userList = userService.getAllFollower();
            if (userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No followers found.", null, null));
            }
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Followers retrieved successfully", userList, null));
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Proper handling of InterruptedException
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving followers", null, e.getMessage()));
        }
    }







}
