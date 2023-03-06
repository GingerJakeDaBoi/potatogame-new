package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.State;
import org.gingerjake.potatogame.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PauseScreen extends State {
    public static boolean isPaused;
    public static boolean isGameOver;
    private int randomImage;
    private static int option;
    private static State prevState;

    public PauseScreen(State currentState) {
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
        switch (randomImage) {
            case 1 ->
                    g.drawImage(new ImageIcon("Assets/Dummy/Menu1.png").getImage(), 0, 0, Game.width,
                            Game.height, null);
            case 2 ->
                    g.drawImage(new ImageIcon("Assets/Dummy/Menu2.jpg").getImage(), 0, 0, Game.width,
                            Game.height, null);
            case 3 ->
                    g.drawImage(new ImageIcon("Assets/Dummy/Menu3.png").getImage(), 0, 0, Game.width,
                            Game.height, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        //TOPBAR
        g.setColor(new Color(80, 80, 80, 255));
        g.fillRect(0, 0, Game.width, (int) (Game.height * .1));

        g.setColor(Color.WHITE);
        if (isPaused) {
            g.drawString(Game.build, 20, 60);
        } else if (isGameOver) {
            g.drawString("GAME OVER", Game.width / 2 - 150, Game.height / 2);
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

        g.setFont(Game.genericFont);
        g.setColor(Color.WHITE);
        g.drawString("Press Enter to Start", (int) (Game.width * .05), Game.height / 2);
    }

    @Override
    public void tick() {

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
            case 0 -> StateManager.setState(Objects.requireNonNullElseGet(prevState, Hub::new));
            case 1 ->Game.exit();
        }
    }
}
