package org.gingerjake.potatogame;

import javax.swing.*;
import java.awt.*;

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
    }
}
