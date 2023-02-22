package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.PlayerController;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    private boolean isRunning = false;
    public static int width = 1600; //1600 //TODO: If aspect ratio is not 16:9, make black bars automatically.
    public static int height = 900; //900
    private static final double frameCap = 60.0; //TODO: Might be able to set in game settings?
    public static int currentFPS = (int) frameCap;
    public static final boolean debug = true;
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

    private void tick() {
        sm.tick();
    }

    private void render() {
        tick();
        repaint();
        invalidate();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        sm.draw(g);
    }

    public void run() { //TODO: Higher FPS caps cause the game to run faster
        long lastTime = System.nanoTime();
        double targetTime = 1000000000 / frameCap;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / targetTime;
            lastTime = now;

            while (delta >= 1) {
                render();
                delta--;
                frames++;
                width = getWidth();
                height = getHeight();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                currentFPS = frames;
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
