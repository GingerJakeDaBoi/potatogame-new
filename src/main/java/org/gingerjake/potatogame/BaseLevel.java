package org.gingerjake.potatogame;

import javax.swing.*;
import java.awt.*;

public class BaseLevel extends State {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();

    public BaseLevel() {
        super(sm);
    }

    @Override
    public void init() {
        Game.player.setSize(108, 192);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Game.player.getFistImg(), Game.player.getFistX(), Game.player.getFistY(), Game.player.getFistWidth(),
                Game.player.getFistHeight(), null);
        g.drawImage(Game.player.getFistImg(), Game.player.getFistX(), Game.player.getFistY(), Game.player.getFistWidth(),
                Game.player.getFistHeight(), null);
        g.drawImage(Game.player.getImg(), Game.player.getX(), Game.player.getY(), Game.player.getWidth(),
                Game.player.getHeight(), null);

        if (Game.player.getHealth() == 4) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            g.drawImage(playerHeart, 156, 0, 48, 48, null);
        }
        if (Game.player.getHealth() == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
        }
        if (Game.player.getHealth() == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
        }
        if (Game.player.getHealth() == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);

        if(Game.debug) {
            g.drawString("Player Location: " + Game.player.getX() + ", " + Game.player.getY(), 0, 80); //each new debug string is + 60 y
        }
    }

    @Override
    public void tick() {
        Game.player.tick();
    }
}
