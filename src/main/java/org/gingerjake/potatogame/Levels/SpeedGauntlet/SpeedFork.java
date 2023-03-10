package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemy;
import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.BaseLevel;
import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.Levels.Hub;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;
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
    final Hitbox hitboxTopBR = new Hitbox((int) (Game.width * .50), (int) (Game.height * .72),Game.width,1,"up");
    final Hitbox hitboxLeftBR = new Hitbox((int) (Game.width * .49), (int) (Game.height * .72),1, Game.height,"left");
    final Hitbox hitboxBottomTR = new Hitbox((int) (Game.width * .50), (int) (Game.height * .25),Game.width,1,"down");
    final Hitbox hitboxLeftTR = new Hitbox((int) (Game.width * .49),0,1, (int) (Game.height * .2),"left");
    final Hitbox hitboxLeft = new Hitbox(0,0, (int) (Game.width * .17),Game.height,"right");

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(400,Game.height - Game.player.getHeight());

        enemy1 = new Enemy(450, 50, 64, 64, 5, 3);
        enemy2 = new Enemy(1350, 300, 64, 64, 5, 3);
        enemy1.setEnabled(true);
        enemy2.setEnabled(true);
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

        hitboxTopBR.tick();
        hitboxLeftBR.tick();
        hitboxBottomTR.tick();
        hitboxLeftTR.tick();
        hitboxLeft.tick();

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
                        currentLvlY += 9;
                        nextUpLvlY += 9;
                    } else {
                        StateManager.setState(new SpeedVertical1());
                    }
                }

                if (switchRight) {
                    Game.player.setPosition(Game.player.getX() + 5,Game.player.getY());

                    if (nextRightLvlX > 0) {
                        currentLvlX -= 18;
                        nextRightLvlX -= 18;
                    } else {
                        StateManager.setState(new Hub());
                    }
                }
            }
        }
    }
}
