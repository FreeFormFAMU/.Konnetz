package com.connetz.connetz.services;


import com.connetz.connetz.controllers.MessageController;
import com.connetz.connetz.util.ApiResponseFormat;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class MessageServices {
    private Firestore firestore;

    public MessageServices () {this.firestore= FirestoreClient.getFirestore(); }



}
