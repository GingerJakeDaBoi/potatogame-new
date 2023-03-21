package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.PlayerController;
import org.gingerjake.potatogame.Levels.GameMenu;
import org.gingerjake.potatogame.Levels.Hub;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Game extends JPanel implements Runnable {
    private boolean isRunning = false;
    public static final String build = "PotatoGame-RW-refactorSpeed-1";
    public static final Font genericFont = new Font("Arial", Font.BOLD, 20);
    public static int width = 1600; //1600 //TODO: If aspect ratio is not 16:9, make black bars
    public static int height = 900; //900
    private static final double frameCap = 60.0; //going to stay 60 at all times
    public static final boolean debug = true;
    private final Input input = new Input();
    private final StateManager sm = new StateManager();
    public static final PlayerController player = new PlayerController();

    public Game() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(input);

        start();
    }

    private static void pause() {
        if(!GameMenu.isInteracting) {
            try {
                StateManager.setState(new GameMenu(StateManager.getState().getDeclaredConstructor().newInstance()));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                StateManager.setState(new GameMenu(new Hub()));
            }

            GameMenu.isInteracting = true;
        }
    }

    private void start() {
        isRunning = true;
        width = getWidth();
        height = getHeight();
        Thread thread = new Thread(this);
        thread.start();
        System.out.println("Guess the game is running now.");
    }

    private void tick() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        getKeys();

        sm.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, width, height);
        sm.draw(g);
    }

    public void getKeys() {
        if(input.isKeyDown(Input.Action.PAUSE)) {
            if(!GameMenu.isPaused) {
                Game.pause();
            } else {
                GameMenu.resume();
            }
        }
        if(!input.isKeyDown(Input.Action.PAUSE)) {
            GameMenu.isInteracting = false;
        }
        if(input.isKeyDown(Input.Action.SELECT)) {
            GameMenu.select();
        }
        if(input.isKeyDown(Input.Action.MOVE_LEFT)) {
            GameMenu.optionDown();
        }
        if(input.isKeyDown(Input.Action.MOVE_RIGHT)) {
            GameMenu.optionUp();
        }
        if(input.isKeyDown(Input.Action.FIST_UP)) {
            Game.player.attack("up");
        }
        if(input.isKeyDown(Input.Action.FIST_DOWN)) {
            Game.player.attack("down");
        }
        if(input.isKeyDown(Input.Action.FIST_LEFT)) {
            Game.player.attack("left");
        }
        if(input.isKeyDown(Input.Action.FIST_RIGHT)) {
            Game.player.attack("right");
        }
        if(input.isKeyDown(Input.Action.FIST_CANCEL)) {
            Game.player.cancelAttack();
        }

        Game.player.setUp(input.isKeyDown(Input.Action.MOVE_UP));
        Game.player.setDown(input.isKeyDown(Input.Action.MOVE_DOWN));
        Game.player.setLeft(input.isKeyDown(Input.Action.MOVE_LEFT));
        Game.player.setRight(input.isKeyDown(Input.Action.MOVE_RIGHT));
    }

    public void run() {
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

            //float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / updateTime)); //TODO: Maybe find out what this is, may help stuttering on 120hz
            try {
                tick();
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }

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

    public static void exit() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
