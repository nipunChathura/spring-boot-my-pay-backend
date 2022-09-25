package lk.ideahub.mypay.advisor;

import lk.ideahub.mypay.exception.EntryDuplicateException;
import lk.ideahub.mypay.exception.EntryNotFoundException;
import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler({EntryDuplicateException.class})
    public ResponseEntity<StandardResponse> entryDuplicateHandler(EntryDuplicateException e){
        return new ResponseEntity<>(
                new StandardResponse(404, "Entry Duplicate Exception", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntryNotFoundException.class})
    public ResponseEntity<StandardResponse> entryNotFoundHandler(EntryNotFoundException e){
        return new ResponseEntity<>(
                new StandardResponse(404, "Entry Not Found Exception", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
