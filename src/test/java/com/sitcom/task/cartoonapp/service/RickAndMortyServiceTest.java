package com.sitcom.task.cartoonapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RickAndMortyServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RickAndMortyService rickAndMortyService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCharacter() throws IOException {
        String name = "Rick Sanchez";
        String apiUrl = "https://rickandmortyapi.com/api/character";
        String allCharactersJson = loadJsonFromFile("characters.json");
        String expected = "{\"id\":1,\"name\":\"Rick Sanchez\",\"status\":\"Alive\",\"species\":\"Human\"}";
        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(allCharactersJson);

        String actual = rickAndMortyService.getCharacter(name);

        assertEquals(expected, actual);
    }

    private String loadJsonFromFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
