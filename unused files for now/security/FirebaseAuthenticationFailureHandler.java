package com.connetz.connetz.security;

import com.connetz.connetz.util.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class FirebaseAuthenticationFailureHandler implements AuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {


        // Set the response status code to 401 (Unauthorized)
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Set the response content type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Write the error message to the response body
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), new ErrorMessage("Authentication failed","", null));
    }

}
