package org.gingerjake.potatogame;

import java.awt.*;

public abstract class State {
    public static StateManager sm;

    public abstract void init();

    public void draw(Graphics g) {
    }

    public void tick() {

    }

    public State(StateManager sm) {
        State.sm = sm;
        init();
    }
}