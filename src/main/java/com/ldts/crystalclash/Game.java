package com.ldts.crystalclash;

import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.states.State;
import com.ldts.crystalclash.viewer.GameViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    public final LanternaGUI gui;
    GameViewer gameViewer;
    GameController gameController;
    private State state;

    public Game(LanternaGUI gui, GameViewer gameViewer, GameController gameController, State state) {
        try {
            this.gui = gui;
            this.gameViewer = gameViewer;
            this.gameController = gameController;
            this.state = new MenuState(new Menu());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        int width = 120; int height = 40;
        LanternaGUI gui = new LanternaGUI(width, height);
        Board board = new Board(8, 8, (width-30), height, 4, 4);
        GameViewer gameViewer = new GameViewer(board);
        GameController gameController = new GameController(board);
        State state = new MenuState(new Menu());
        Game game = new Game(gui, gameViewer, gameController, state);
        game.start();

    }

    private void start() throws IOException {
        try {
            boolean running = true;
            while (running) {
                gameViewer.draw(gui);
                LanternaGUI.ACTION action = gui.getNextAction();
                if (action == GUI.ACTION.QUIT) { running = false; }
                else {
                    gameController.step(this, action);
                }
            }
            closeGame();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(State state) {
        this.state = state;
    }



    private void closeGame() throws IOException {
        gui.clear();
        gui.close();
    }
}
