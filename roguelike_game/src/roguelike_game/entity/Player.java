/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import roguelike_game.graphics.Sprite;
import roguelike_game.transport.Inventory;

/**
 *
 * @author andyafw
 */
public class Player {
    private int x;
    private int y;
    private Sprite sprite;
    private Inventory inventory;
    
    public Player(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        inventory = new Inventory(7, 7);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    } 
    
    public Inventory getInventory() {
        return inventory;
    }
}
