package com.dkm.supermarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =  HttpStatus.NOT_FOUND, reason = "该用户已禁止登陆！")
public class BanException extends RuntimeException{
}
