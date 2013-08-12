/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import java.awt.Graphics;
import roguelike_game.TileMap;
import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
public class Player {
    private int x;
    private int y;
    private int size;
    private Sprite sprite;
    private TileMap map;
    
    public Player(int x, int y, int size, Sprite sprite, TileMap map) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.sprite = sprite;
        this.map = map;
    }
    
    public void render(Graphics g) {
        g.drawImage(sprite.getImage(), x * size, y * size, size, size, null);
    }
}
