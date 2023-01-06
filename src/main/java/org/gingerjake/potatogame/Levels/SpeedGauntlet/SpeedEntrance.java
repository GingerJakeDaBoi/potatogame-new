package org.gingerjake.potatogame.Levels.SpeedGauntlet;

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

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(400, Game.height - Game.player.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, Game.width, Game.height, null);
        g.drawImage(nextLvl, nextLvlX, nextLvlY, Game.width, Game.height, null);
        super.draw(g);
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
    }
}
