package com.gabriel.apiplantoes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorDTO {

    private HttpStatus httpStatus;
    private String message;

}
