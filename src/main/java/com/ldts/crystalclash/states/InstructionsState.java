package com.ldts.crystalclash.states;


import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.ScoresMenuController;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.viewer.InstructionsViewer;
import com.ldts.crystalclash.viewer.ScoresMenuViewer;
import com.ldts.crystalclash.viewer.Viewer;

public class InstructuinsState extends State<Instructions> {
    public InstructuinsState(Instructions model) {
        super(model);
    }

    @Override
    protected Viewer<Instructions> getViewer() {
        return new InstructionsViewer(getModel());
    }

    @Override
    protected Controller<Instructions> getController() {
        return new InstructionsController(getModel());
    }
}

