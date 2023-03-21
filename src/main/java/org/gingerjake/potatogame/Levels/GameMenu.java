package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.State;
import org.gingerjake.potatogame.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameMenu extends State {
    public static boolean isPaused;
    public static boolean isGameOver;
    public static boolean isInteracting;
    private int randomImage;
    private static int option;
    private static State prevState;

    public GameMenu(State currentState) {
        super(sm);
        prevState = currentState;
    }

    @Override
    public void init() {
        option = 0;
        isPaused = true;
        randomImage = (int) (Math.random() * 3 + 1); //TODO: Use random class
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.width,Game.height);

        if(!isGameOver) {
            switch (randomImage) {
                case 1 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu1.png").getImage(), 0, 0, Game.width,
                        Game.height, null);
                case 2 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu2.jpg").getImage(), 0, 0, Game.width,
                        Game.height, null);
                case 3 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu3.png").getImage(), 0, 0, Game.width,
                        Game.height, null);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        //TOPBAR
        g.setColor(new Color(80, 80, 80, 255));
        g.fillRect(0, 0, Game.width, (int) (Game.height * .1));

        g.setColor(Color.WHITE);
        if (isGameOver) {
            g.drawString("GAME OVER", Game.width / 2 - 150, Game.height / 2);
        } else if (isPaused) {
            g.drawString(Game.build, 20, 60);
        }

        g.setColor(new Color(80, 80, 80, 255));
        g.fillRect(0, Game.height - 85, Game.width, Game.height);

        g.setColor(Color.WHITE);
        g.setColor(new Color(100, 100, 100, 255));
        switch (option) {
            case 0 -> g.fillRect(0, Game.height - 85, 240, Game.height);
            case 1 -> g.fillRect(240, Game.height - 85, 160, Game.height);
        }

        g.setColor(Color.WHITE);
        g.drawString("Resume", 20, (int) (Game.height * .97));
        g.drawString("Quit", 260, (int) (Game.height * .97));

        g.setColor(Color.BLACK);
    }

    public static void optionUp() {
        if(option < 1) {
            option++;
        }
    }

    public static void optionDown() {
        if(option > 0) {
            option--;
        }
    }

    public static void select() {
        switch(option) {
            case 0 -> resume();
            case 1 -> Game.exit();
        }
    }

    public static void resume() {
        if(!isInteracting) {
            StateManager.setState(Objects.requireNonNullElseGet(prevState, Hub::new));

            if (isGameOver) {
                isGameOver = false;
            }

            isPaused = false;
            isInteracting = true;
        }
    }
}
