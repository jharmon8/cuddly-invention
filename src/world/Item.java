package world;

import java.awt.image.BufferedImage;

public class Item {
    private BufferedImage image;

    public Item(int imageNum, Itemset itemSet) {
        this.image = itemSet.getItemImage(imageNum);
    }

    public BufferedImage getImage() {
        return image;
    }
}
