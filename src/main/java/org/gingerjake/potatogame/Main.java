package org.gingerjake.potatogame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Potato Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(Game.width, Game.height);
        gameFrame.setLocationRelativeTo(null);

        gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("Assets/GUI/Heart.png"));
        gameFrame.add(new Game(), BorderLayout.CENTER);
        gameFrame.setResizable(true);
        gameFrame.setVisible(true);

        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Game.player.setUp(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Game.player.setDown(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Game.player.setLeft(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Game.player.setRight(true);
                }
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    Game.player.attack("up");
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    Game.player.attack("left");
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    Game.player.attack("down");
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    Game.player.attack("right");
                }
                if(e.getKeyCode() == KeyEvent.VK_P) {
                    Game.player.hurt();
                    System.out.println(Game.player.getHealth());
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Game.player.cancelAttack();
                }
            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Game.player.setUp(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Game.player.setDown(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Game.player.setLeft(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Game.player.setRight(false);
                }
            }
            return false;
        });
    }
}
