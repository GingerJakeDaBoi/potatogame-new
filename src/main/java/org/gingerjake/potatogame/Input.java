package org.gingerjake.potatogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Input implements KeyListener {

    private final HashMap<Integer, Boolean> keyStates = new HashMap<>();
    private final HashMap<Integer, Integer> keyBindings = new HashMap<>();

    public Input() { //Default binds go here
        //Player Movement
        keyBindings.put(KeyEvent.VK_LEFT, Action.MOVE_LEFT);
        keyBindings.put(KeyEvent.VK_RIGHT, Action.MOVE_RIGHT);
        keyBindings.put(KeyEvent.VK_UP, Action.MOVE_UP);
        keyBindings.put(KeyEvent.VK_DOWN, Action.MOVE_DOWN);

        //Attacks
        keyBindings.put(KeyEvent.VK_W, Action.FIST_UP);
        keyBindings.put(KeyEvent.VK_A, Action.FIST_LEFT);
        keyBindings.put(KeyEvent.VK_S, Action.FIST_DOWN);
        keyBindings.put(KeyEvent.VK_D, Action.FIST_RIGHT);
        keyBindings.put(KeyEvent.VK_SPACE, Action.FIST_CANCEL);

        //Misc.
        keyBindings.put(KeyEvent.VK_ESCAPE, Action.PAUSE);
        keyBindings.put(KeyEvent.VK_ENTER, Action.SELECT);
    }

    public void bindKey(int keyCode, int action) {
        keyBindings.put(keyCode, action);
    }

    public boolean isKeyDown(int action) {
        for (Map.Entry<Integer, Integer> entry : keyBindings.entrySet()) {
            if (entry.getValue() == action) {
                return keyStates.getOrDefault(entry.getKey(), false);
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    public interface Action {
        int MOVE_LEFT = 0;
        int MOVE_RIGHT = 1;
        int MOVE_UP = 2;
        int MOVE_DOWN = 3;
        int FIST_LEFT = 4;
        int FIST_RIGHT = 5;
        int FIST_UP = 6;
        int FIST_DOWN = 7;
        int FIST_CANCEL = 8;
        int PAUSE = 9;
        int SELECT = 10;
    }

}