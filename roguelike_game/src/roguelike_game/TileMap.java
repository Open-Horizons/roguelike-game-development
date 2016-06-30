package roguelike_game;

/*
 * Class used to make an array of tiles
 */

import java.util.Random;
import roguelike_game.entity.item.Item;

/**
 *
 * @author andy Walser
 * @version 10-20-13
 */

//used to draw the world map
public class TileMap {
    
    public int width;
    public int height;
    public int size;
    public int[][] tiles;
    public Item[][] items;
    
    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        size = 32;
        tiles = new int[height][width];
        items = new Item[height][width];
    }
    
    public void createRandomMap() {
        Random rand = new Random(10);
        randomTiles(rand);
        randomItems(rand);
    }
    
    public void randomTiles(Random rand) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int r = rand.nextInt(3);
                tiles[y][x] = r;
            }
        } 
    }
    
    public void randomItems(Random rand) {
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(tiles[y][x] != 0) {
                    int r = rand.nextInt(20);
                    switch(r) {
                        case 0:
                            items[y][x] = Item.SWORD;
                            break;
                        case 1:
                            items[y][x] = Item.WAND;
                            break;
                    }
                }
            }
        }
    }
    
    public void inputMap(String file) {
        
    }
}
