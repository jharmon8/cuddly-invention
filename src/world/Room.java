package world;

public class Room {
    private int[][] layer1;
    private int[][] layer2;
    public Tileset tileset;

    public int width;
    public int height;

    public Room(int[][] layer1, int[][] layer2, Tileset tileset) {
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.tileset = tileset;

        this.width = layer1.length;
        this.height = layer1[0].length;


    }

    public int[][] getLayer1() {
        return layer1;
    }

    public int[][] getLayer2() {
        return layer2;
    }

    public boolean inBounds(int x, int y) {
        if(x < 0 || y < 0) { return false; }
        if(x >= width || y >= height) { return false; }

        return true;
    }
}
