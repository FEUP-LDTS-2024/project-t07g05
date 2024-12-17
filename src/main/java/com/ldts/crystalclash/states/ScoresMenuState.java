package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.ScoresMenuController;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.viewer.ScoresMenuViewer;
import com.ldts.crystalclash.viewer.Viewer;

public class ScoresMenuState extends State<ScoresMenu> {
    public ScoresMenuState(ScoresMenu model) {
        super(model);
    }

    @Override
    protected Viewer<ScoresMenu> getViewer() {
        return new ScoresMenuViewer(getModel());
    }

    @Override
    protected Controller<ScoresMenu> getController() {
        return new ScoresMenuController(getModel());
    }
}
