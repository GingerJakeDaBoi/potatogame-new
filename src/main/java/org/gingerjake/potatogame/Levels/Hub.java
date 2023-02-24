package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.BaseLevel;
import org.gingerjake.potatogame.Game;

import javax.swing.*;
import java.awt.*;

public class Hub extends BaseLevel {
    final Image background = new ImageIcon("Assets/Dummy/GreenDitherBG.png").getImage();

    @Override
    public void init() {
        super.init();
        Game.player.setPosition(500, 500);
        Game.player.setHealth(3);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Game.width, Game.height, null);

        super.draw(g);
    }

    @Override
    public void tick() {

        if (Game.player.getY() <= 0) {
//            StateManager.setState(new SpeedEntrance());
        }


        super.tick();
    }
}
