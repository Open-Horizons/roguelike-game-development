/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import roguelike_game.entity.item.Item;
import roguelike_game.transport.Inventory;

/**
 *
 * @author andyafw
 */
public class Mob extends Entity {
    protected int hp;
    protected int xp;
    private Inventory inventory;
    
    public Mob(int x, int y) {
        super(x, y, 32, 32);
        inventory = new Inventory(7, 7);
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public void pickupItem(Item item) {
        inventory.addItem(item);
    }
    
    public void equipItem(Item item) {
        inventory.addEquip(item);
    }
    
    public void dropItem(Item item) {
        
    }
    
    public void unequipItem(Item item) {
        
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getHp() {
        return hp;
    }
    
    public void setXp(int xp) {
        this.xp = xp;
    }
    
    public int getXp() {
        return xp;
    }
}
