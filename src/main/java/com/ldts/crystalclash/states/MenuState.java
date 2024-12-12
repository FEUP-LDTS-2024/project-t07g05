package com.ldts.crystalclash.states;
import com.ldts.crystalclash.viewer.MenuViewer;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.MenuController;
import com.ldts.crystalclash.model.Menu;

import com.ldts.crystalclash.viewer.Viewer;

public class MenuState extends State<Menu>{
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel()) ;
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}

