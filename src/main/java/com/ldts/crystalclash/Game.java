package com.ldts.crystalclash;


import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.Music;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    public LanternaGUI gui;
    private State state;
    private int width = 120;
    private int height = 40;
    Music music = new Music();


    public Game() throws IOException, URISyntaxException, FontFormatException{
        this.gui = new LanternaGUI(width, height);
        this.state = new MenuState(new Menu());
    }


    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();
    }

    private void start() throws IOException{
        int FPS = 10;
        int frameTime = 1000 / FPS;
        music.startMusic();
      
        while (state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Caught exception in game loop");
            }
        }
        closeGame();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void closeGame() throws IOException {
        gui.close();
    }
}