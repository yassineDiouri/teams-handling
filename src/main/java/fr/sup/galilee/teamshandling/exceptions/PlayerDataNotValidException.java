package fr.sup.galilee.teamshandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Player data not valid")
public class PlayerDataNotValidException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Player data not valid";
    }
}
