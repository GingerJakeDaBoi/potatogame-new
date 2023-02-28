package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Game;
import org.gingerjake.potatogame.State;

import java.awt.*;

public class PauseScreen extends State {
    public PauseScreen() {
        super(sm);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.width,Game.height);

        g.setFont(Game.genericFont);
        g.setColor(Color.WHITE);
        g.drawString("Press Enter to Start", (int) (Game.width * .05), Game.height / 2);
    }
}
