/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import roguelike_game.TileMap;
import roguelike_game.entity.item.Item;
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
    
    protected int items_can_hold = 49;
    protected int items_can_wear = 10;//helmet, hands, chest armor, belt, ring, pants, left-hand sword, amulet, boots, and right-hand sword
    protected ArrayList<Item> equippedItems = new ArrayList<Item>(10);
    protected ArrayList<Item> inventory = new ArrayList<Item>(49);
    
    public Player(int x, int y, int size, Sprite sprite, TileMap map) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.sprite = sprite;
        this.map = map;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void render(Graphics g, int scrollx, int scrolly) {
        g.drawImage(sprite.getImage(), x * size - scrollx, y * size - scrolly, size, size, null);
    }
    
    public void addItem(Item item) {
        if(inventory.size() != items_can_hold) {
            inventory.add(item);
        } else {
            //TODO: need to figure out a way to tell user inventory is full
        }
    }
    
    public Item getItem(int x, int y) {
        int pos = y * 7 + x;
        if(inventory.size() > pos) {
            if(inventory.get(pos) != null) {
                return inventory.get(pos);
            } else {
                //no item to return;
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void addEquip(Item item) {
        if(equippedItems.size() <= items_can_wear) {
            equippedItems.add(item);
        } else {
            //can not add item
        }
    }
    
    public Item getEquip(int i) {
        if(i < equippedItems.size()) {
            return equippedItems.get(i);
        } else {
            return null;
        }
    }
    
}
