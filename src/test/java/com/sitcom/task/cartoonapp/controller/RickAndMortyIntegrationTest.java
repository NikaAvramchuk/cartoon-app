package com.sitcom.task.cartoonapp.controller;

import com.sitcom.task.cartoonapp.exceptions.CharacterNotFoundException;
import com.sitcom.task.cartoonapp.service.RickAndMortyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RickAndMortyController.class)
public class RickAndMortyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RickAndMortyService rickAndMortyService;

    @InjectMocks
    private RickAndMortyController rickAndMortyController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnCorrectCharacter() throws Exception {
        String characterName = "Rick";
        String characterData = "{'id': 1, 'name': 'Rick Sanchez'}";
        when(rickAndMortyService.getCharacter(characterName)).thenReturn(characterData);

        mockMvc.perform(get("/api/rickandmorty").param("name", characterName))
                .andExpect(status().isOk())
                .andExpect(content().json(characterData));
    }

    @Test
    public void shouldThrowCharacterNotFoundExceptionForNotExistingCharacter() throws Exception {
        String nonExistingCharacterName = "NonExistingCharacter";
        when(rickAndMortyService.getCharacter(nonExistingCharacterName)).thenReturn("");

        mockMvc.perform(get("/api/rickandmorty").param("name", nonExistingCharacterName))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CharacterNotFoundException))
                .andReturn();
    }
}
