package com.sitcom.task.cartoonapp.exceptions;

public class CharacterNotFoundException extends RuntimeException{
    private final String characterName;
    public CharacterNotFoundException(String characterName) {
        super("Character : " + characterName + " not found.");
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }
}
