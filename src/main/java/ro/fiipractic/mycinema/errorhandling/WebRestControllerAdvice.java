package ro.fiipractic.mycinema.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.logging.Logger;

@RestControllerAdvice
public class WebRestControllerAdvice {

    private static final Logger logger = Logger.getLogger(WebRestControllerAdvice.class.getName());

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleBadRequestException(BadRequestException ex) {
        logger.info("EXCEPTION: Bad Request");
        return this.generateErrorDto(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException ex) {
        logger.info("EXCEPTION: Not Found");
        return this.generateErrorDto(HttpStatus.NOT_FOUND, ex);
    }

    // Default handler for exceptions that may occur in the application
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto defaultHandler(Exception ex) {
        logger.info("EXCEPTION: Internal Server Error");
        return this.generateErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ExceptionDto generateErrorDto(HttpStatus status, Exception ex) {
        return new ExceptionDto(status.value(), ex.getMessage());
    }
}
