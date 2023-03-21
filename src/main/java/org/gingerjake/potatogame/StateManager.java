package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Levels.Hub;
import org.gingerjake.potatogame.Levels.GameMenu;

import java.awt.*;
import java.util.Stack;

public class StateManager {
    public static Stack<State> states;

    public StateManager() {
        states = new Stack<>();
        states.push(new GameMenu(new Hub()));
    }

    public static void setState(State state) {
        states.push(state);
    }

    public static Class<? extends State> getState() {
        return states.peek().getClass();
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}
