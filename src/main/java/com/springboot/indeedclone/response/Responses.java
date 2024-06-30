package com.springboot.indeedclone.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Responses {
    public static ResponseEntity<ApiResponse<?>> badRequest (String operation, String object){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.set("Could not " + operation + " " + object + ", please check all required fields are correct");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ApiResponse<?>> notFound (String object){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.set("No "+ object + " with that id were found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
