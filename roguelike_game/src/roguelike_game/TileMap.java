package roguelike_game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import roguelike_game.entity.item.Item;
import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */

//used to draw the world map
public class TileMap {
    private Roguelike_game game;
    
    public int width;
    public int height;
    public int size;
    public int[][] tiles;
    public Item[][] items;
    
    public TileMap(Roguelike_game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        size = 32;
        tiles = new int[height][width];
        items = new Item[height][width];
    }
    
    public void randomMap() {
        Random rand = new Random(10);
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int r = rand.nextInt(3);
                System.out.println("rand = " + r);
                tiles[y][x] = r;
            }
        } 
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
    
    public void render(Graphics g, int scrollx, int scrolly) {
        int startx = Math.max(scrollx / size, 0);
        int starty = Math.max(scrolly / size, 0);
        int limitx = Math.min((scrollx + game.painting.width) / 30 + 1, width);
        int limity = Math.min((scrolly + game.painting.height) / 30 + 1, height);
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                g.drawImage(findImage(tiles[y][x]), x * size - scrollx, y * size - scrolly, size, size, null);
            }
        }
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                if(items[y][x] == null) {
                    continue;
                } else {
                    g.drawImage(items[y][x].getSprite().getImage(), x * size - scrollx, y * size - scrolly, size, size, null);
                }
            }
        }   
    }
    
    private Image findImage(int i) {
        switch(i) {
            case 0:
                return Sprite.WALL.getImage();
            default:
                //if don't know number inputed then send floor image
                return Sprite.FLOOR.getImage();
        }
    }
}
