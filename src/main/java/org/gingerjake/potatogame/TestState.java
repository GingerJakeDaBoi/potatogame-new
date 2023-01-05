package org.gingerjake.potatogame;

import java.awt.*;

public class TestState extends State {
    public TestState() {
        super(sm);
    }

    @Override
    public void init() {
        Game.player.setPosition(100,100);
        Game.player.setSize(108,192);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.player.getFistImg(),Game.player.getFistX(),Game.player.getFistY(),Game.player.getFistWidth(),
                Game.player.getFistHeight(),null);
        g.drawImage(Game.player.getFistImg(),Game.player.getFistX(),Game.player.getFistY(),Game.player.getFistWidth(),
                Game.player.getFistHeight(),null);
        g.drawImage(Game.player.getImg(),Game.player.getX(),Game.player.getY(),Game.player.getWidth(),
                Game.player.getHeight(),null);
    }

    @Override
    public void tick() {
        System.out.println("Tick!");
    }
}
