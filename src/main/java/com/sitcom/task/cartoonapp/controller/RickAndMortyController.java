package com.sitcom.task.cartoonapp.controller;

import com.sitcom.task.cartoonapp.exceptions.CharacterNotFoundException;
import com.sitcom.task.cartoonapp.service.RickAndMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling requests related to animated characters from Rick and Morty.
 */
@RestController
@RequestMapping("api/rickandmorty")
public class RickAndMortyController {
    @Autowired
    private RickAndMortyService rickAndMortyService;

    /**
     * Retrieves information about an animated character by name.
     *
     * @param name The name of the character to retrieve.
     * @return ResponseEntity containing the character information and HTTP status code.
     * @throws CharacterNotFoundException if the character with the specified name is not found.
     */
    @GetMapping
    public ResponseEntity<String> getCharacter(@RequestParam String name) {
        String character = rickAndMortyService.getCharacter(name);

        if (character.isEmpty()) {
            throw new CharacterNotFoundException(name);
        }
        return new ResponseEntity<>(character, HttpStatus.OK);
    }
}
