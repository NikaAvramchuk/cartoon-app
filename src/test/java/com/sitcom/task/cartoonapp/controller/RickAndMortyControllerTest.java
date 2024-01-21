package com.sitcom.task.cartoonapp.controller;

import com.sitcom.task.cartoonapp.exceptions.CharacterNotFoundException;
import com.sitcom.task.cartoonapp.service.RickAndMortyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RickAndMortyControllerTest {
    @Mock
    private RickAndMortyService rickAndMortyService;

    @InjectMocks
    private RickAndMortyController rickAndMortyController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnCorrectCharacter() {
        String characterName = "Rick";
        String characterData = "{'id': 1, 'name': 'Rick Sanchez'}";
        when(rickAndMortyService.getCharacter(characterName)).thenReturn(characterData);

        ResponseEntity<String> response = rickAndMortyController.getCharacter(characterName);

        verify(rickAndMortyService, times(1)).getCharacter(characterName);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(response.getBody(), characterData)
        );
    }

    @Test
    public void shouldThrowCharacterNotFoundExceptionForNotExistingCharacter() {
        String nonExistingCharacterName = "NonExistingCharacter";
        when(rickAndMortyService.getCharacter(nonExistingCharacterName)).thenReturn("");

        assertThrows(CharacterNotFoundException.class, () ->
                rickAndMortyController.getCharacter(nonExistingCharacterName));
    }
}
