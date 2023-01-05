package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.PlayerController;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    private boolean isRunning = false;
    public static int width = 1600; //1600
    public static int height = 900; //900
    private StateManager sm = new StateManager();
    public static final PlayerController player = new PlayerController();

    public Game() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        start();
    }

    private void start() {
        isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void tick() {
        sm.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        sm.draw(g);
    }

    public void run() {
        sm = new StateManager();

        while (isRunning) {
            tick();
            repaint();
            invalidate();

            int FPS = 60;
            long TARGET_TIME = (1000 / FPS);
            try {
                //noinspection BusyWait
                Thread.sleep(TARGET_TIME);

                //update width and height of the screen
                width = getWidth();
                height = getHeight();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
