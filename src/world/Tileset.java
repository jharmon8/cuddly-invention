package world;

import util.ResourceUtil;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tileset {
    private int tilesize;
    private int width;
    private int height;

    private BufferedImage[] tiles;

    public Tileset(int tilesize, BufferedImage img) {
        this.tilesize = tilesize;
        this.width = img.getWidth()/tilesize;
        this.height = img.getHeight()/tilesize;

        this.tiles = new BufferedImage[height*width];

        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                tiles[j*width+i] = img.getSubimage(i*tilesize,j*tilesize,tilesize,tilesize);
            }
        }
    }

    public BufferedImage getTile(int tilenum) {
        return tiles[tilenum];
    }

    public int getTilesize() {
        return tilesize;
    }
}
