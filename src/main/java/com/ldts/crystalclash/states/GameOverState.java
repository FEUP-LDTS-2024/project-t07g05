package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.GameOverController;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.viewer.GameOverViewer;
import com.ldts.crystalclash.viewer.Viewer;

public class GameOverState extends State<GameOver>{
    public GameOverState(GameOver model) {
        super(model);
    }

    @Override
    protected Viewer<GameOver> getViewer() {
        return new GameOverViewer(getModel());
    }

    @Override
    protected Controller<GameOver> getController() {
        return new GameOverController(getModel());
    }
}
