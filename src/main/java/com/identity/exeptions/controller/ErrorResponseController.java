package com.identity.exeptions.controller;

import com.identity.exeptions.ErrorResponse;
import com.identity.exeptions.exceptions.RoleNotFound;
import com.identity.exeptions.exceptions.UserNotFound;
import com.identity.exeptions.exceptions.VeryWeakPassword;
import com.identity.exeptions.exceptions.WrongPassword;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseController {

    @ExceptionHandler(VeryWeakPassword.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleVeryWeakPassword(VeryWeakPassword v) {
        return new ErrorResponse("The password provided is too weak, please try again.");
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse userNotFoundHandler(UserNotFound u) {
        return new ErrorResponse("Username does not exist");
    }

    @ExceptionHandler(WrongPassword.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse wrongPasswordHandler(WrongPassword w) {
        return new ErrorResponse("The password entered is wrong, please try again");
    }

    @ExceptionHandler(RoleNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse roleNotFoundHandler(RoleNotFound r){
        return new ErrorResponse("Role not found");
    }

}
