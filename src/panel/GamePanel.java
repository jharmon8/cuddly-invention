package panel;

import util.ResourceUtil;
import world.Room;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends Panel {
    private Room room;

    public GamePanel(int minX, int minY) {
        super(minX, minY);

        this.room = ResourceUtil.getRoom("BiggerRoom");

        this.width = room.width * room.tileset.getTilesize();
        this.height = room.height * room.tileset.getTilesize();

        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    @Override
    public BufferedImage draw() {
        Graphics g = canvas.createGraphics();

        for(int i = 0; i < room.width; i++) {
            for(int j = 0; j < room.height; j++) {
                g.drawImage(
                        room.tileset.getTile(room.getLayer1()[i][j]),
                        i*room.tileset.getTilesize(),
                        j*room.tileset.getTilesize(),
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

        return canvas;
    }
}
