package com.sitcom.task.cartoonapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RickAndMortyService {
    private final String apiUrl = "https://rickandmortyapi.com/api/character";
    @Autowired
    private RestTemplate restTemplate;
    public String getCharacter(String name) {
        String allCharacters = restTemplate.getForObject(apiUrl, String.class);
        return findCharacterByName(allCharacters, name);
    }

    private String findCharacterByName(String allCharacters, String targetName) {;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode allCharactersNode = objectMapper.readTree(allCharacters);
            for (JsonNode next : allCharactersNode.at("/results")) {
                String name = next.path("name").asText().replace("\"", "");;
                if (targetName.equals(name)) {
                    return next.toString();
                }
            }

        } catch (JsonProcessingException e) {
            log.error("Can't process all characters node {}", e.getMessage());
        }
        return "";
    }
}
