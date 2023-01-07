package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemy;
import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.BaseLevel;
import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.Levels.Hub;
import org.gingerjake.potatogame.StateManager;

import javax.swing.*;
import java.awt.*;

public class SpeedEntrance extends BaseLevel {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    boolean finished = true; //TODO: Change to false
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -Game.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    final Hitbox hitbox1 = new Hitbox(773,0,811,861,"left");
    //hitbox for the left side
    final Hitbox hitbox2 = new Hitbox(0,0,276,861,"right");
    Enemy enemy;

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(400, Game.height - Game.player.getHeight());
        enemy = new Enemy(450,50,64,64,5,3);
        enemy.setEnabled(true);

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, Game.width, Game.height, null);
        g.drawImage(nextLvl, nextLvlX, nextLvlY, Game.width, Game.height, null);

        if(enemy.isEnabled()) {
            g.drawImage(enemy.getAsset(), enemy.getX(), enemy.getY(),enemy.getWidth(),
                    enemy.getHeight(), null);
        }

        if(!switching) {
            super.draw(g);
        }
    }

    @Override
    public void tick() {
        if (finished) {
            if (Game.player.getY() <= 0) {
                switching = true;
            }
            if (switching) {
                if (nextLvlY < 0) {
                    currentLvlY += 9;
                    nextLvlY += 9;
                } else {
                    StateManager.setState(new Hub()); //TODO: Change to next level
                }
            }
        }
        super.tick();
        hitbox1.tick();
        hitbox2.tick();
        enemy.tick();
    }
}
