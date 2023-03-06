package org.gingerjake.potatogame.Actors;

import org.gingerjake.potatogame.Game;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int health;
    private final int speed;
    private boolean enabled;
    private final Image asset;

    public Enemy(int x, int y, int width, int height, int health, int speed) {
        this.x = x;
        this.y = y;
        this.width = (int) (width * ((double) (Game.width * Game.height) / (double) (1600 * 900)));
        this.height = (int) (height * ((double) (Game.width * Game.height) / (double) (1600 * 900)));
        this.health = health;
        this.speed = speed;
        this.asset = new ImageIcon("Assets/Dummy/Red.png").getImage();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        this.setHealth(this.getHealth()-damage);
    }

    public Image getAsset() {
        return asset;
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

    public int getHealth() {
        return health;
    }

    public void tick() {
        if (Game.player.getX() > this.x) {
            this.x += this.speed;
        } else if (Game.player.getX() < this.x) {
            this.x -= this.speed;
        }
        if (Game.player.getY() > this.y) {
            this.y += this.speed;
        } else if (Game.player.getY() < this.y) {
            this.y -= this.speed;
        }

        checkForFist();
        checkForPlayer();
        checkForDeath();
    }

    private void checkForDeath() {
        if (this.isDead()) {
            this.setEnabled(false);
        }
        if (this.getHealth() <= 0) {
            this.setEnabled(false);
        }
    }

    private void checkForPlayer() {
        if (!this.enabled) {
            return;
        }
        if (Game.player.getX() + Game.player.getWidth() > x && Game.player.getX() < x + width &&
                Game.player.getY() + Game.player.getHeight() > y && Game.player.getY() < y + height) {
            Game.player.hurt();
        }

    }

    private void checkForFist() {
        if (!Game.player.isThrowFist()) {
            return;
        }
        if (Game.player.getFistX() + Game.player.getFistScale() <= x || Game.player.getFistX() >= x +
                width || Game.player.getFistY() + Game.player.getFistScale() <= y || Game.player.getFistY()
                >= y + height) {
            return;
        }
        this.takeDamage(1);
        System.out.println("Hit enemy!");
        if (Game.player.isThrowFist()) {
            Game.player.cancelAttack();
        }
    }
}
