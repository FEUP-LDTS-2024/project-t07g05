package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.viewer.GameOverViewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;

public class GameOverController extends Controller<GameOver> {
    private String nickNameSpaces;
    private String nickName;
    private static final int LIMITCHAR = 11;
    private Integer newScore;
    List<Integer> scores;
    List<String> names;


    public GameOverController(GameOver model) {
        super(model);
    }


    public void keyPressed(KeyEvent e) {

        boolean isNumber = e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9;
        boolean isLetterMin = e.getKeyCode() > 96 && e.getKeyCode() < 123;
        boolean isCapitalLetter = e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z;

        if (isLetterMin || isCapitalLetter || isNumber)
            if (KeyEvent.getKeyText(e.getKeyCode()).length() == 1)
                writeName(e.getKeyChar());

         if (e.getKeyCode() == KeyEvent.VK_ENTER){
            //updateLeaderboard(Constants.LEADERBOARD_FILE);
        }
    }

    public void writeName(char c) {
        if(nickName.length() + 1 <= LIMITCHAR) nickName += c;
        nickNameSpaces = "";
        for (char ch : nickName.toCharArray()) {
            nickNameSpaces += ch + " ";
        }
        for (int i = 0; i < (LIMITCHAR - nickName.length()); i++)
            nickNameSpaces += "_ ";

    }


    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().selectPrevious();
                break;
            case DOWN:
                getModel().selectNext();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedPlayAgain())
                    game.setState(new GameState(new Board(8, 8, 90, 40, 4, 4)));
                //completar para o menuscores
                if (getModel().isSelectedScores()) {

                }
                break;
            default:

        }

    }

}
