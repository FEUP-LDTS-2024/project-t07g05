package com.ldts.crystalclash.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.states.MenuState;

import java.io.IOException;

public class ScoresMenuController extends Controller<ScoresMenu> {
    public ScoresMenuController(ScoresMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT) {
            // Quando o jogador pressiona "q" (ação de QUIT), muda o estado para o MenuState.
            game.setState(new MenuState(new Menu()));
        }
    }
}
