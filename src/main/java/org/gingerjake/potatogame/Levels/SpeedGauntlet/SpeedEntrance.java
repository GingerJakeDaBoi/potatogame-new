package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemy;
import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.BaseLevel;
import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.StateManager;

import javax.swing.*;
import java.awt.*;

public class SpeedEntrance extends BaseLevel {
    private final Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    private final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    private boolean finished = false;
    private boolean switching;
    private int nextLvlY = -Game.height;
    private int currentLvlY = 0;
    //Hitbox for the right side
    private final Hitbox hitbox1 = new Hitbox((int) (Game.width * .17),0,1,861,"right");//TODO: Could probably make them NOT hard coded???
    //hitbox for the left side
    private final Hitbox hitbox2 = new Hitbox((int) (Game.width * .485),0,1,861,"left");
    private Enemy enemy;

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(400, Game.height - Game.player.getHeight());
        enemy = new Enemy(450,50,64,64,5,3);
        enemy.setEnabled(true);

    }

    @Override
    public void draw(Graphics g) {
        int currentLvlX = 0;
        g.drawImage(background, currentLvlX, currentLvlY, Game.width, Game.height, null);
        int nextLvlX = 0;
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
                    StateManager.setState(new SpeedFork());
                }
            }
        }
        super.tick();
        hitbox1.tick();
        hitbox2.tick();
        if(!enemy.isDead()) {
            enemy.tick();
        }
        if(!finished) {
            finished = true;
        }
    }
}
