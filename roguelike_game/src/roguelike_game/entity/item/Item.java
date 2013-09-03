/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity.item;

import java.awt.Graphics;
import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
public class Item {
    public static Item weapon = new Item(Sprite.EMPTY);
    
    protected int x, y;
    protected Sprite sprite;
    
    public Item(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void render(Graphics g, int xscroll, int yscroll) {
        if(sprite.getImage() != null) {
            g.drawImage(sprite.getImage(), x * sprite.width, y * sprite.height, sprite.width, sprite.height, null);
        }
    }
}