package com.dkm.supermarket.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.dkm.supermarket.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IncorrectCredentialsException.class)
    public String incorrectCredentialsExceptionHandler(Exception e){
        return "<script>alert('ERROR Incorrect username or password！');history.go(-1);</script>";
    }


    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedExceptionHandler(Exception e){
        return "<script>alert('Your privileges are insufficient！');history.go(-1);</script>";
    }

    @ResponseBody
    @ExceptionHandler(BanException.class)
    public String banExceptionHandler(Exception e){
        return "<script>alert('Your account has been banned！');history.go(-1);</script>";
    }

    @ResponseBody
    @ExceptionHandler(UpdateException.class)
    public String updateExceptionHandler(Exception e){
        return "<script>alert('Order submission failed！');history.go(-1);</script>";
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        return "<script>alert('ERROR！');history.go(-1);</script>";
    }

}
