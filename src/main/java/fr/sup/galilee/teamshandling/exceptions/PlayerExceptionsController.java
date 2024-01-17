package fr.sup.galilee.teamshandling.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerExceptionsController {

    @ExceptionHandler(PlayerIdRequiredException.class)
    public ResponseEntity<String> playerIdRequired(PlayerIdRequiredException e) {
        return ResponseEntity
                .badRequest()
                .body("The player : " + e.getPlayerDTO().getFirstname() + "does not have a valid ID");
    }
}
