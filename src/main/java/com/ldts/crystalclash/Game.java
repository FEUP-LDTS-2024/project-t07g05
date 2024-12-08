package com.ldts.crystalclash;

import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.viewer.GameViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    public final LanternaGUI gui;
    GameViewer gameViewer;
    GameController gameController;
    Timer timer;

    public Game(LanternaGUI gui, GameViewer gameViewer, GameController gameController) {
        try {
            this.gui = gui;
            this.gameViewer = gameViewer;
            this.gameController = gameController;
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


        new Game(gui, gameViewer, gameController).start();
    }

    private void closeGame() throws IOException {
        gui.clear();
        gui.close();
    }

    private void start() throws IOException {
        try {
            this.timer = new Timer(()-> {
                try {
                    closeGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            timer.start();
            boolean running = true;
            while (running) {
                System.out.println(timer.getTimeLeft());
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
}
