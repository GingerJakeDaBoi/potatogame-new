package org.gingerjake.potatogame.Actors;

import org.gingerjake.potatogame.Game;

public class Hitbox {
    final int x;
    final int y;
    final int width;
    final int height;
    final String direction;

    public Hitbox(int x, int y, int width, int height, String pushDirection) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = pushDirection;
    }

    public void tick() {
        if (Game.player.getX() + Game.player.getWidth() > this.x && Game.player.getX() < this.x + this.width && Game.player.getY() + Game.player.getHeight() > this.y && Game.player.getY() < this.y + this.height) {
            switch (this.direction) {
                case "left" -> Game.player.setPosition(this.x - Game.player.getWidth(),
                        Game.player.getY());
                case "right" -> Game.player.setPosition(this.x + this.width, Game.player.getY());
                case "up" ->
                        Game.player.setPosition(Game.player.getX(), this.y - Game.player.getHeight());
                case "down" -> Game.player.setPosition(Game.player.getX(), this.y + this.height);
            }
        }
    }
}