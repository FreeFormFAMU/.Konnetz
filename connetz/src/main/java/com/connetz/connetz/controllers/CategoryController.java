package com.connetz.connetz.controllers;

import com.connetz.connetz.services.CategoryServices;
import com.connetz.connetz.util.ErrorMessage;
import com.connetz.connetz.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController //identified this class a controller used for REST API class.
@RequestMapping("/api/category")//sets up the base resource url for all calls to methods in this file
public class CategoryController {


    private final CategoryServices categoryServices;

    @Value("${response.status}")
    private int statusCode;

    @Value("${response.name}")
    private String name;

    private Object payload;
    private ResponseWrapper response;
    private static final String CLASS_NAME = "CategoryService";

    public CategoryController()
    {
        this.categoryServices = null;
        payload = null;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String,Object>> getCategories(){
        try{
            payload = categoryServices.getCategories();
            statusCode = 200;
            name = "categories";
        } catch (ExecutionException | InterruptedException e) {
            payload = new ErrorMessage("Cannot fetch categories from database.",CLASS_NAME, e.toString());
        }
        response = new ResponseWrapper(statusCode,name, payload);

        return response.getResponse();
    }
}

