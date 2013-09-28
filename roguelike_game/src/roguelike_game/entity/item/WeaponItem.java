/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity.item;

import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
public class WeaponItem extends Item {
    protected int damage = 0;
    protected int health = 0;
    
    public WeaponItem(Sprite sprite, int damage, int health) {
        super(Item.Type.WEAPON, sprite);
        this.damage = damage;
        this.health = health;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int heal) {
        health = heal;
    }
    
    public void setDamage(int dam) {
        damage = dam;
    }
}
