package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(LanternaGUI gui);
}
