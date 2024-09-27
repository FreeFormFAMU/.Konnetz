package com.connetz.connetz.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class ResponseWrapper {

    private int statusCode;
    private String name;
    private Object payload;

    public ResponseWrapper(int statusCode, String name, Object payload) {
        this.statusCode = statusCode;
        this.name = name;
        this.payload = payload;
    }

    // Getter methods for statusCode, name, and payload (optional)

    public ResponseEntity getResponse()
    {
        return ResponseEntity.status(statusCode).body(Collections.singletonMap(name, payload));
    }
}