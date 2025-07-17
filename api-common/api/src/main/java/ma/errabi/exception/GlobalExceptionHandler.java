package ma.errabi.exception;

import lombok.extern.slf4j.Slf4j;
import ma.errabi.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildError(ErrorConstants.NOT_FOUND_CODE, ex.getMessage()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse> handleInvalidInput(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildError(ErrorConstants.INVALID_INPUT_CODE, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllUnhandledErrors(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(ErrorConstants.SERVER_ERROR_CODE, ex.getMessage()));
    }

    private ApiResponse buildError(String errorCode, String errorDesc) {
        return ApiResponse.builder()
                .errorCode(errorCode)
                .errorDescription(errorDesc)
                .timestamp(Instant.now())
                .build();
    }
}
