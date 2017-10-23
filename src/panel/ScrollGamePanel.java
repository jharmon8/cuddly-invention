package panel;

import util.ControlUtil;
import util.ResourceUtil;
import world.Direction;
import world.Player;
import world.Room;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ScrollGamePanel extends Panel {
    private Room room;

    private int numTilesX = 12;
    private int numTilesY = 6;

    private Player player;

    public ScrollGamePanel(int minX, int minY) {
        super(minX, minY);

        this.room = ResourceUtil.getRoom("CustomRoom");

        this.width = numTilesX * room.tileset.getTilesize();
        this.height = numTilesY * room.tileset.getTilesize();

        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        this.player = new Player(8,8, ResourceUtil.getSprite("TestSprite"), room);
    }

    @Override
    public BufferedImage draw() {
        Graphics g = canvas.createGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        for(int i = 0; i < room.width; i++) {
            for(int j = 0; j < room.height; j++) {
                g.drawImage(
                        room.tileset.getTile(room.getLayer1()[i][j]),
                        i*room.tileset.getTilesize() - player.drawX + width/2 - 16,
                        j*room.tileset.getTilesize() - player.drawY + height/2 - 16,
                        null
                );

/*                g.drawImage(
                        room.tileset.getTile(room.getLayer2()[i][j]),
                        i*room.tileset.getTilesize(),
                        j*room.tileset.getTilesize(),
                        null
                );*/
            }
        }

        g.drawImage(
                player.getImage(),
                width/2 - 16,
                height/2 - 16,
                null
        );

        return canvas;
    }

    @Override
    public void update() {
/*
        for (Map.Entry<Character, Boolean> entry : controls.entrySet()) {
            Character key = entry.getKey();
            Object value = entry.getValue();

            if(entry.getValue() && isWASD(entry.getKey())) {
                Direction d = new Direction(entry.getKey());

                if(!player.direction.equals(d)) {
                    player.direction = d;
                    break;
                }

                player.walk(d);
                break;
            }
        }
*/
        if(!ControlUtil.chatting) {
            for(Character c : ControlUtil.controls) {
                if(isWASD(c)) {
                    Direction d = new Direction(c);

                    if(!player.direction.equals(d)) {
                        player.direction = d;
                        break;
                    }

                    player.walk(d);
                    break;
                }
            }
        }

        player.update();
    }

    private boolean isWASD(char c) {
        return c == 'w' || c == 'a' || c == 's' || c == 'd';
    }
}
