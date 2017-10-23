package panel;

import util.ResourceUtil;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class RedPanel extends Panel {
    public RedPanel(int minX, int minY) {
        super(minX, minY);
    }

    @Override
    public BufferedImage draw() {
        BufferedImage img = ResourceUtil.getImage("Red");
        return img;
    }
}
