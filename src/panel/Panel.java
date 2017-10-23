package panel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class Panel {

    protected int minX, minY, width, height;
    protected BufferedImage canvas;

    public Panel(int minX, int minY) {
        this.minX = minX;
        this.minY = minY;
    }

    public abstract BufferedImage draw();

    // click, key
    // mouse enter, mouse exit
    public void mousePress(MouseEvent e) {}
    public void mousePress(int x, int y) {}
    public void mouseMove(MouseEvent e) {}
    public void keyPress(KeyEvent e) {}
    public void keyType(KeyEvent e) {}
    public void keyRelease(KeyEvent e) {}

    public void update() {}
    public void show() {}
    public void hide() {}
    public void setHidden(boolean isHidden) {}

    public int getMinX() {return minX;}
    public int getMinY() {return minY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public boolean collides(int x, int y) {
        boolean withinWidth = (x < minX + width && x > minX);
        boolean withinHeight = (y < minY + height && y > minY);

        return (withinWidth && withinHeight);
    }
}
