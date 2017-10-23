package util;

import world.Itemset;
import world.Room;
import world.Sprite;
import world.Tileset;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class ResourceUtil {

    private static HashMap<String, BufferedImage> images = new HashMap<>();
    private static HashMap<String, Tileset> tilesets = new HashMap<>();
    private static HashMap<String, Sprite> sprites = new HashMap<>();
    private static HashMap<String, Room> rooms = new HashMap<>();
    private static HashMap<String, Itemset> itemsets = new HashMap<>();

    private static final int tilesize = 32;
    private static final int framesize = 32;
    private static final int itemsize = 24;

    public static void init() {
        populateImages();
        populateTilesets();
        populateRooms();
        populateSprites();
        populateItemsets();
    }

    private static void populateItemsets() {
        // populate tilesets
        try (Stream<Path> paths = Files.walk(Paths.get("res/items"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filename -> {
                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new File(filename.toString()));
                                    itemsets.put(
                                            filename.getFileName().toString().split("\\.")[0],
                                            new Itemset(itemsize,img)
                                    );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateSprites() {
        try (Stream<Path> paths = Files.walk(Paths.get("res/sprites"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filename -> {
                                BufferedImage img = null;
                                    try {
                                    img = ImageIO.read(new File(filename.toString()));
                                    sprites.put(
                                            filename.getFileName().toString().split("\\.")[0],
                                            new Sprite(framesize,img)
                                    );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateTilesets() {
        // populate tilesets
        try (Stream<Path> paths = Files.walk(Paths.get("res/tileset"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filename -> {
                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new File(filename.toString()));
                                    tilesets.put(
                                            filename.getFileName().toString().split("\\.")[0],
                                            new Tileset(tilesize,img)
                                    );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateRooms() {
        // populate tilesets
        try (Stream<Path> paths = Files.walk(Paths.get("res/room"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filename -> {
                        rooms.put(
                                filename.getFileName().toString().split("\\.")[0],
                                new Room(loadCSV(filename.toString()), null, getTileset("SemiTiles"))
                        );
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] loadCSV(String filename) {
        ArrayList<String[]> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String line;
            String separator = ",";

            while ((line = reader.readLine()) != null) {

                // use comma as separator
                lines.add(line.split(separator));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] output = new int[lines.get(0).length][lines.size()];

        for(int i = 0; i < output.length; i++) {
            for(int j = 0; j < output[0].length; j++) {
                output[i][j] = Integer.parseInt(lines.get(j)[i]);
            }
        }

        return output;
    }

    private static void populateImages() {
        // populate images
        try (Stream<Path> paths = Files.walk(Paths.get("res/img"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filename -> {
                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new File(filename.toString()));
                                    images.put(filename.getFileName().toString().split("\\.")[0], img);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String filename) {
        return images.get(filename);
    }

    public static Room getRoom(String filename) {
        return rooms.get(filename);
    }

    public static Tileset getTileset(String filename) {
        return tilesets.get(filename);
    }

    public static Itemset getItemset(String filename) {
        return itemsets.get(filename);
    }

    public static Sprite getSprite(String filename) {
        return sprites.get(filename);
    }

    public static Color[][] toColors(byte[] pixels, int width, int height) {
/*        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(result[i][j]);
            }
        }
*/
        Color[][] output = new Color[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                output[i][j] = Color.red;
            }
        }

        return output;
    }
}
