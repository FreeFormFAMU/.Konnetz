package com.connetz.connetz.controllers;

import com.connetz.connetz.security.FirebaseUserDetails;
import com.connetz.connetz.util.JwtUtil;
import com.connetz.connetz.util.LoginRequest;
import com.connetz.connetz.util.RegistrationRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

    private final FirebaseAuth firebaseAuth;

    @Autowired
    private final AuthenticationManager authenticationManager;

    private final Log logger = LogFactory.getLog(this.getClass());

    public AuthenticationController(FirebaseAuth firebaseAuth, AuthenticationManager authenticationManager) {
        this.firebaseAuth = firebaseAuth;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest registrationRequest) throws FirebaseAuthException {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail(registrationRequest.getEmail())
                .setPassword(registrationRequest.getPassword())
                .setDisplayName(registrationRequest.getDisplayName());
        UserRecord userRecord = firebaseAuth.createUser(createRequest);
        return "User created with UID: " + userRecord.getUid();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) throws FirebaseAuthException {
        UserRecord userRecord = firebaseAuth.getUserByEmail(loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRecord.getUid(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = new FirebaseUserDetails(userRecord);
        String token = JwtUtil.generateToken(userDetails);
        logger.info(token);
        return token;
    }




    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logged out successfully";
    }
}
