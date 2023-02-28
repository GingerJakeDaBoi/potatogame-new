package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemy;
import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.BaseLevel;
import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.Levels.Hub;
import org.gingerjake.potatogame.StateManager;

import javax.swing.*;
import java.awt.*;

public class SpeedFork extends BaseLevel {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    final Image nextUpLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    final Image nextRightLvl = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    Enemy enemy1,enemy2;
    boolean finished = false;
    boolean switching;
    boolean switchUp;
    boolean switchRight;
    final int nextUpLvlX = 0;
    int nextUpLvlY = -Game.height;
    int nextRightLvlX = Game.width;
    final int nextRightLvlY = 0;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the top part of the bottom right side
    final Hitbox hitbox1a = new Hitbox(780,621,Game.width,1,"up");
    //now the left part
    final Hitbox hitbox1b = new Hitbox(773,621,1, Game.height,"left");
    //Hitbox for the bottom part of the top right side
    final Hitbox hitbox2a = new Hitbox(790,225,Game.width,1,"down");
    //now the left part
    final Hitbox hitbox2b = new Hitbox(773,0,1,205,"left");
    //hitbox for the right side
    final Hitbox hitbox3 = new Hitbox(0,0,276,861,"right");

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(400,Game.height - Game.player.getHeight());

        enemy1 = new Enemy(450, 50, 64, 64, 5, 3);
        enemy2 = new Enemy(1350, 300, 64, 64, 5, 3);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, Game.width, Game.height, null);
        g.drawImage(nextUpLvl, nextUpLvlX, nextUpLvlY, Game.width, Game.height, null);
        g.drawImage(nextRightLvl, nextRightLvlX, nextRightLvlY, Game.width, Game.height, null);

        if(!enemy1.isDead()) {
            g.drawImage(enemy1.getAsset(), enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight(), null);
        }
        if(!enemy2.isDead()) {
            g.drawImage(enemy2.getAsset(), enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight(), null);
        }

        if(!switching) {
            super.draw(g);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!enemy1.isDead()) { //TODO: Enemies don't deal damage
            enemy1.tick();
        }
        if(!enemy2.isDead()) {
            enemy2.tick();
        }

        if (enemy1.isDead() && enemy2.isDead()) {
            finished = true;
        }

        hitbox1a.tick();
        hitbox1b.tick();
        hitbox2a.tick();
        hitbox2b.tick();
        hitbox3.tick();

        if (finished) {
            if (Game.player.getY() == 0) {
                switching = true;
                switchUp = true;
                switchRight = false;
            }
            if (Game.player.getX() == Game.width - Game.player.getWidth()) {
                switching = true;
                switchUp = false;
                switchRight = true;
            }

            if (switching) {
                if (switchUp) {
                    Game.player.setPosition(Game.player.getX(),Game.player.getY()-5);

                    if (nextUpLvlY < 0) {
                        currentLvlY += 3;
                        nextUpLvlY += 3;
                    } else {
                        StateManager.setState(new Hub());
                    }
                }

                if (switchRight) {
                    Game.player.setPosition(Game.player.getX() + 5,Game.player.getY());

                    if (nextRightLvlX > 0) {
                        currentLvlX -= 6;
                        nextRightLvlX -= 6;
                    } else {
                        StateManager.setState(new Hub());
                    }
                }
            }
        }
    }
}
