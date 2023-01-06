package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Levels.Hub;

import java.awt.*;
import java.util.Stack;

public class StateManager {
    public static Stack<State> states;

    public StateManager() {
        states = new Stack<>();
        states.push(new Hub());
    }

    public static void setState(State state) {
        states.push(state);
    }

    public static String getState() {
        return states.peek().getClass().getSimpleName();
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}
