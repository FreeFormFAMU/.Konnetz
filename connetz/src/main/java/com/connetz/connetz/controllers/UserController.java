package com.connetz.connetz.controllers;
import com.connetz.connetz.models.user.User;
import com.connetz.connetz.services.UserServices;
import com.connetz.connetz.util.ApiResponseFormat;
import com.connetz.connetz.util.ErrorMessage;
import com.connetz.connetz.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;



// 2/21/2024
// In the User control should have the follow_user, unfollow_user, and skills contorller.

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserServices userService;

    @Value("${response.status}")
    private int statusCode;

    @Value("${response.name}")
    private String name;

    private Object payload;

    private ResponseWrapper response;

    private static final String CLASS_NAME = "UserService";


    public UserController(UserServices userService) {
        this.userService = userService;
        payload = null;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        try {
            payload = userService.getAllUsers();
            statusCode = 200;
            name = "user";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch users from database", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }


    // Get the user by their id
    @GetMapping("/{id}") // check
    public ResponseEntity<Map<String, Object>> getUserByID(@PathVariable(name = "id") String id) {
        try {
            payload = userService.getUserById(id);
            statusCode = 200;
            name = "user";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch user with id " + id + " from database.", CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }


    // Get followers by Id
    /*@GetMapping("followers/{followers_id}") // trouble
    public ResponseEntity<ApiResponseFormat<List<User>>> getFollowUser(@PathVariable(name="followers_id") String followersId) {
        try{
            List<User> users = userService.getUserByFollower(followersId);

            if (users != null)
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User Found", users, null));
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
    }*/

    @PostMapping("/") // check
    public ResponseEntity<ApiResponseFormat<String>> addUser(@RequestBody(required = false) User user) {
        try {
            payload = userService.createUser(user);
            statusCode = 201;
            name = "userId";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot create new user in database.", CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable(name = "userId") String id, @RequestBody Map<String, String> updateValues) {


        try {

            userService.updateUserInformation(id, updateValues);
            statusCode = 201;
            name = "message";
            payload = "Update successful for user with id " + id;

        } catch (Exception e) {
            payload = new ErrorMessage("Cannot update user with id " + id, CLASS_NAME, e.toString());
        }

        response = new ResponseWrapper(statusCode, name, payload);

        return response.getResponse();
    }
}

    /*@GetMapping("/following") // returns all the users but not specific following
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
    }*/

    /*@GetMapping("/followers") // Same things gets all the followers
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







}*/