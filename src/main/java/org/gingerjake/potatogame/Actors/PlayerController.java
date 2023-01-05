package org.gingerjake.potatogame.Actors;

import org.gingerjake.potatogame.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerController {
    private final Image pRight = new ImageIcon("Assets/Potato/NewMainR.png").getImage();
    private final Image pLeft = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
    private final Image attackU = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
    private final Image attackD = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();
    private final Image attackL = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
    private final Image attackR = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
    private int x;
    private int y;
    private int width;
    private int height;
    private int fistX;
    private int fistY;
    private static int health;
    private static boolean hurting;
    private boolean throwFist;
    private boolean uping;
    private boolean downing;
    private boolean lefting;
    private boolean righting;
    private String playerDirection = "right";
    private String fistDirection = null;

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void setSize(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
    }

    public void setPlayerDirection(String newDir) {
        playerDirection = newDir;
    }

    public void setUp(boolean up) {
        uping = up;
    }

    public void setDown(boolean down) {
        downing = down;
    }

    public void setLeft(boolean left) {
        lefting = left;
    }

    public void setRight(boolean right) {
        righting = right;
    }

    public Image getFistImg() {
        if (fistDirection != null) {
            switch (fistDirection) {
                case "up" -> {
                    return attackU;
                }
                case "down" -> {
                    return attackD;
                }
                case "left" -> {
                    return attackL;
                }
                case "right" -> {
                    return attackR;
                }
                default -> {
                    System.out.println("Invalid attack direction.");
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Image getImg() {
        if (playerDirection.equals("left")) {
            return pLeft;
        } else {
            return pRight;
        }
    }

    public boolean isThrowFist() {
        return throwFist;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFistX() {
        return fistX;
    }

    public int getFistY() {
        return fistY;
    }

    public int getFistWidth() {
        return (int) (width / 1.5);
    }

    public int getFistHeight() {
        return height / 2;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void attack(String throwDirection) {
        if (fistDirection == null) {
            switch (throwDirection) {
                case "up" -> fistDirection = "up";
                case "down" -> fistDirection = "down";
                case "left" -> fistDirection = "left";
                case "right" -> fistDirection = "right";
                default -> System.out.println("Invalid or wrong fist direction! Not throwing.");
            }
            throwFist = true;
            fistX = x;
            fistY = y;
        }
    }

    public void cancelAttack() {
        throwFist = false;
        fistDirection = null;
    }

    public void hurt() {
        if (!hurting) {
            health -= 1;
            hurting = true;

            //if the player was hurt, they can't be hurt again for 1 second
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    hurting = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public void tick() {
        int speed = 5;
        if (uping)
            y -= speed;
        if (downing)
            y += speed;
        if (lefting) {
            x -= speed;
            setPlayerDirection("left");
        }
        if (righting) {
            x += speed;
            setPlayerDirection("right");
        }

        if (fistDirection != null && throwFist) {
            switch (fistDirection) {
                case "up" -> fistY -= 5;
                case "down" -> fistY += 5;
                case "left" -> fistX -= 5;
                case "right" -> fistX += 5;
            }
        }

        if (getFistX() > Game.width - getFistWidth() || getFistX() < 0 || getFistY() > Game.height - getFistHeight() || getFistY() < 0) {
            cancelAttack();
        }

        if(getHealth() <= 0) {
            System.out.println("Game Over");
            System.exit(0);
        }
    }
}
