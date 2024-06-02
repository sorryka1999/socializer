package socializer.exception;

import socializer.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomWrongInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<MessageDto> handleCustomWrongInputException(CustomWrongInputException exception) {
        MessageDto message = new MessageDto(exception.getMessage());
        log.error("handleCustomWrongInputException: {}", exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<MessageDto> handleExceptionInternal(Exception exception) {
        MessageDto message = new MessageDto(exception.getMessage());
        log.error("handleExceptionInternal: {}", exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<List<MessageDto>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<MessageDto> responses = new LinkedList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String exceptionMessage = ((FieldError) error).getField() + " " + error.getDefaultMessage();
            MessageDto message = new MessageDto(exceptionMessage);
            log.error("Field not valid exception: {}, {}", ((FieldError) error).getField(), error.getDefaultMessage());
            responses.add(message);
        });
        return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
    }

}
