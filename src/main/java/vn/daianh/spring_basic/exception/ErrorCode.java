package vn.daianh.spring_basic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {   // Dùng để biểu diễn mã lỗi
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorize error!",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Invalid message key",HttpStatus.BAD_REQUEST ),
    USER_EXISTED(1002,"User existed",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at {min} characters",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1004, "Username must be at {min} characters",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed",HttpStatus.NOT_FOUND),
    AUTHENTICATED(1006, "Authenticated",HttpStatus.UNAUTHORIZED),  //500
    UNAUTHORIZED(1007, "You do not have permission",HttpStatus.FORBIDDEN),  //403
    INVALID_DOB(1008, "Your age must bt at least {min}",HttpStatus.BAD_REQUEST)  //403

    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

}