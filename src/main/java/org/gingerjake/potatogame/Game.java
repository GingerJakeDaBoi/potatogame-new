package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.PlayerController;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    private boolean isRunning = false;
    public static int width = 1600; //1600
    public static int height = 900; //900
    private final StateManager sm = new StateManager();
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

    public void run() { //TODO: Add an FPS cap so the game doesn't generate 1.5M frames per second
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (isRunning) {
                repaint();
                invalidate();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        exit();
    }

    public static void exit() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
