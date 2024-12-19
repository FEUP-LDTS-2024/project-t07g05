package com.ldts.crystalclash.model;

public class Instructions {
    private final String text;

    public Instructions() {
        this.text = """
            Welcome to Crystal Clash!
            
            Controls:
            - Arrow Keys: Move around
            - Enter: Select
            - Q: Quit
            
            Objective:
            Match crystals to score points.
            
            Good luck!
            """;
    }

    public String getText() {
        return text;
    }
}