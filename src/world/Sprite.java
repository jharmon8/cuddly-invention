package world;

import java.awt.image.BufferedImage;

public class Sprite {
    private int framesize;
    private BufferedImage[][] frames;

    private int width;
    private int height;

    public Sprite(int framesize, BufferedImage img) {
        this.framesize = framesize;
        this.width = img.getWidth()/framesize;
        this.height = img.getHeight()/framesize;

        this.frames = new BufferedImage[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                frames[i][j] = img.getSubimage(i*framesize,j*framesize,framesize,framesize);
            }
        }
    }

    public BufferedImage[] getAnimation(int animNumber) {
        if(animNumber < frames.length) {
            return frames[animNumber];
        }
        return null;
    }

    public BufferedImage getFrame(int animNumber, int frameNumber) {
        if(animNumber < frames.length) {
            if(frameNumber < frames[animNumber].length) {
                return frames[animNumber][frameNumber];
            }
        }
        return null;
    }

    public int getFramesize() {
        return framesize;
    }
}
