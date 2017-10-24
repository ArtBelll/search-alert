package ru.korbit.saserver.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExist extends RuntimeException {

    public AlreadyExist(String message) {
        super(message);
    }

}
