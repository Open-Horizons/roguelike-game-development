package roguelike_game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
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
    
    public TileMap(Roguelike_game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        size = 32;
        tiles = new int[height][width];
    }
    
    public void randomMap() {
        Random rand = new Random(1200);
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int r = rand.nextInt(3);
                System.out.println("rand = " + r);
                tiles[y][x] = r;
            }
        }
    }
    
    public void inputMap(String file) {
        
    }
    
    public void render(Graphics g) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                g.drawImage(findImage(tiles[y][x]), x * size, y * size, size, size, null);
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
