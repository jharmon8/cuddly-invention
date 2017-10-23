package panel;

import util.ResourceUtil;
import world.Tileset;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;

public class GreenPanel extends Panel {

    private Tileset tileset;

    public GreenPanel(int minX, int minY) {
        super(minX, minY);

        this.width = 32;
        this.height = 32;

        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    @Override
    public BufferedImage draw() {
        Graphics g = canvas.createGraphics();

        BufferedImage green = ResourceUtil.getImage("Green");
        BufferedImage purple = ResourceUtil.getImage("Purple");

        g.drawImage(green, 0, 0, null);
        g.drawImage(purple, 16, 16, null);

        return canvas;
    }
}
