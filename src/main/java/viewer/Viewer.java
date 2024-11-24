package viewer;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(TextGraphics graphics);
}
