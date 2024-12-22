package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.InstructionsController;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.viewer.InstructionsViewer;
import com.ldts.crystalclash.viewer.Viewer;

public class InstructionsState extends State<Instructions>{
    public InstructionsState(Instructions model) {
        super(model);
    }

    @Override
    protected Viewer<Instructions> getViewer() {
        return new InstructionsViewer(getModel()) ;
    }

    @Override
    protected Controller<Instructions> getController() {
        return new InstructionsController(getModel());
    }
}
