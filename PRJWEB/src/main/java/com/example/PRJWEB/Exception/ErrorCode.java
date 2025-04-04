package com.example.PRJWEB.Exception;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public enum ErrorCode {

    UNCATEGORIZE_EXCEPTION(999 , "Uncategorized error" , HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1000, "Invalid message key" , HttpStatus.BAD_REQUEST),
    USER_EXISTED( 1001 , "User existed", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1002, "Password must be at least 8 characters" , HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1002, "Password required" ,HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters" ,HttpStatus.BAD_REQUEST),
    USERNAME_REQUIRED(1003, "Username required" ,HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED( 1004 , "User not existed" ,HttpStatus.NOT_FOUND),
    UNAUTHENTICATED( 1005 , "unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN);
    ;
    int code;
    String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code , String message ,HttpStatusCode httpStatusCode) {
        this.code=code;
        this.message=message;
        this.httpStatusCode = httpStatusCode;
    }
}
