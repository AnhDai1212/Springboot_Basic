package vn.daianh.spring_basic.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.daianh.spring_basic.dto.request.ApiResponse;

import java.util.Map;
import java.util.Objects;

// Nơi đây cau hinh cac error dien ra duoi tang filer sau khi xuong service sinh ra error

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    private static final String MIN_ATTRIBUTE = "min";
//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<String> handlerRuntineException (RuntimeException exception) {
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
    @ExceptionHandler(value = Exception.class)  // Ngoại lệ cuối cung khi ko có method nào hứng nó
    public ResponseEntity<ApiResponse> handlerRuntineException(RuntimeException exception) {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;



        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)  // Những truy cập bị từ chối
    public ResponseEntity<ApiResponse> handlerAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode  = ErrorCode.UNAUTHORIZED;

        ApiResponse apiResponse = new ApiResponse();
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(
                        ApiResponse.builder()
                                .code(errorCode.getCode())
                                .message(errorCode.getMessage())
                                .build()
                );
    }


    @ExceptionHandler(value = AppException.class)   // Những ngoại lệ tự cấu hình
    public ResponseEntity<ApiResponse> handlingException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
//        ErrorCode errorCode = ErrorCode.USER_EXISTED;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)  //MethodArgumentNotValidException  sẽ bắt các kiệu ValidException
    public ResponseEntity<ApiResponse> handlingValidation (MethodArgumentNotValidException exception) {
        String enumkey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        Map<String, Object> attributes = null;
        try{
            errorCode = ErrorCode.valueOf(enumkey);

            var constrainViolation = exception.getBindingResult()
                    .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constrainViolation.getConstraintDescriptor().getAttributes(); // lay cac param da truyen vao @constrain

            log.info(attributes.toString());

        } catch (IllegalArgumentException e){

        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(Objects.nonNull(attributes) ?
                 mapAttribute(errorCode.getMessage(),attributes) : errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);

//        return ResponseEntity.badRequest().body(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
    }

    private String mapAttribute(String message, Map<String,Object> attributes) {
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();
        return message.replace("{"+ MIN_ATTRIBUTE + "}", minValue);
    }



}
