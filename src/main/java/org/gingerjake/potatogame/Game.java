package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.PlayerController;
import org.gingerjake.potatogame.Levels.Hub;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    private boolean isRunning = false;
    private static boolean gameStarted = false;
    public static int width = 1600; //1600 //TODO: If aspect ratio is not 16:9, make black bars
    // automatically.
    public static int height = 900; //900 //TODO: Make it possible to change resolution without the
    // game being sad, might fix with a title screen?
    private static final double frameCap = 60.0; //TODO: Might be able to set in game settings?
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
        width = getWidth();
        height = getHeight();
        Thread thread = new Thread(this);
        thread.start();
    }

    private void tick() {
        sm.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        sm.draw(g);
    }

    public void run() { //TODO: Higher FPS caps cause the game to run faster
        final double updateTime = 1000000000 / frameCap;

        final int maxUpdatesBeforeRender = 60;
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        while (isRunning) {
            double now = System.nanoTime();
            int updateCount = 0;

            //Do as many game updates as we need to, potentially playing catchup.
            while (now - lastUpdateTime > updateTime && updateCount < maxUpdatesBeforeRender) {
                repaint();

                lastUpdateTime += updateTime;
                updateCount++;
            }

            if (now - lastUpdateTime > updateTime) {
                lastUpdateTime = now - updateTime;
            }

            //float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / updateTime)); //TODO: Maybe find out what this is
            tick();

            lastRenderTime = now;

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while (now - lastRenderTime < updateTime && now - lastUpdateTime < updateTime) {
                Thread.yield();

                try {
                    //noinspection BusyWait
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                now = System.nanoTime();
            }

            width = getWidth();
            height = getHeight();
        }
        exit();
    }

    public static void beginGame() {
        if(!gameStarted) {
            StateManager.setState(new Hub());
            gameStarted = true;
        }
    }

    public static void exit() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
