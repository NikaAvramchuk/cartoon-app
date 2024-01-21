package com.sitcom.task.cartoonapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CharacterNotFoundException.class)
    public String userNotFoundException(CharacterNotFoundException exception, Model model) {
        log.error("Character not found");
        model.addAttribute("characterName", exception.getCharacterName());
        return "error";
    }
}
