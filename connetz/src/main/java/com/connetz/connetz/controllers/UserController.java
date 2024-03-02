package com.connetz.connetz.controllers;

import com.connetz.connetz.services.UserServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//Made by Malachi White
// 2/21/2024
// In the User control should have the follow_user, unfollow_user, and skills contorller.

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServices userServices;

    public void UserServices(UserServices userServices) {
        this.userServices = userServices;
    }
}
