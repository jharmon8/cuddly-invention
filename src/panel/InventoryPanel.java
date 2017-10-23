package panel;

import util.ResourceUtil;
import world.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class InventoryPanel extends Panel {
    int numItemsX = 4;
    int numItemsY = 7;

    private int itemsize = 24;

    private Item[] items = new Item[numItemsX * numItemsY];

    public InventoryPanel(int minX, int minY) {
        super(minX, minY);

        this.width = numItemsX*itemsize;
        this.height = numItemsY*itemsize;

        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        items[0] = new Item(0, ResourceUtil.getItemset("DownloadItems"));
        items[1] = new Item(27, ResourceUtil.getItemset("DownloadItems"));
        items[2] = new Item(36, ResourceUtil.getItemset("DownloadItems"));
        items[4] = new Item(30, ResourceUtil.getItemset("DownloadItems"));
    }

    @Override
    public BufferedImage draw() {
        Graphics g = canvas.createGraphics();

        g.setColor(new Color(80,66,42));
        g.fillRect(0, 0, width, height);

        for(int i = 0; i < numItemsX; i++) {
            for(int j = 0; j < numItemsY; j++) {
                if(items[j*numItemsX + i] != null) {
                    g.drawImage(
                            items[j*numItemsX + i].getImage(),
                            i*itemsize,
                            j*itemsize,
                            null
                    );
                }
            }
        }

        return canvas;
    }
}
