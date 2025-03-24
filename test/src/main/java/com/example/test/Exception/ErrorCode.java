package com.example.test.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZE_EXCEPTION(999 , "Uncategorize error" , HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1000, "Invalid message key" , HttpStatus.BAD_REQUEST),
    USER_EXISTED( 101 , "User existed" , HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1002, "Password must be at least 8 characters" , HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Ussername must be at least 3 characters" , HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED( 104 , "User not existed" , HttpStatus.NOT_FOUND),
    UNAUTHENTICATED( 105 , "unauthenticated" , HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED( 106 , "you do not have permission" , HttpStatus.FORBIDDEN)

    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code , String message ,HttpStatusCode httpStatusCode) {
        this.code=code;
        this.message=message;
        this.httpStatusCode = httpStatusCode;
    }
}
