package world;

import java.awt.image.BufferedImage;

public class Player {
    public Sprite sprite;
    public Room room;

    public int x;
    public int y;

    private int currAnim = -1, currFrame = 0;

    private int debugx, debugy;

    public int drawX, drawY;

    public Direction direction;

    public Player(int x, int y, Sprite sprite, Room room) {
        this.x = x;
        this.y = y;

        this.drawX = x*32;
        this.drawY = y*32;

        this.sprite = sprite;
        this.room = room;

        this.direction = new Direction(3);
    }

    public boolean walk(Direction d) {

        if(currAnim >= 0) {
            return false;
        }

        this.direction = d;

        int dX = d.getXShift();
        int dY = d.getYShift();

        if(!room.inBounds(x + dX, y + dY)) {
            return false;
        }

        x = x + dX;
        y = y + dY;

        currAnim = d.value;

        return true;
    }

    public BufferedImage getImage() {
        if(currAnim < 0) {
            return sprite.getFrame(direction.value, 0);
        }

        return sprite.getFrame(currAnim, currFrame);
/*
        drawX = 0;
        drawY = 0;

        debugx++;

        if(debugx > 3) {
            debugy++;
            debugx = 0;
        }

        if(debugy > 2) {
            debugy = 0;
        }

        return sprite.getFrame(debugx,debugy);
*/
    }

    public void update() {
        drawX = x*32;
        drawY = y*32;

        if(currAnim < 0) {
            return;
        }

        currFrame++;

        if(currFrame >= sprite.getAnimation(currAnim).length) {
            currAnim = -1;
            currFrame = 0;
            return;
        }

        // shitty hack to translate
        drawX = direction.getXShift() * (-32 / 3) * (3 - currFrame) + x * 32;
        drawY = direction.getYShift() * (-32 / 3) * (3 - currFrame) + y * 32;
    }
}
