package world;

import java.awt.image.BufferedImage;

public class Itemset {
    private int itemsize;
    private int width;
    private int height;

    private BufferedImage[] tiles;

    public Itemset(int itemsize, BufferedImage img) {
        this.itemsize = itemsize;
        this.width = img.getWidth()/itemsize;
        this.height = img.getHeight()/itemsize;

        this.tiles = new BufferedImage[height*width];

        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                tiles[j*width+i] = img.getSubimage(i*itemsize,j*itemsize,itemsize,itemsize);
            }
        }
    }

    public BufferedImage getItemImage(int itemnum) {
        return tiles[itemnum];
    }

    public int getItemsize() {
        return itemsize;
    }
}
