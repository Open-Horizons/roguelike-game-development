/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
public abstract class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Sprite sprite;
    
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
    
    public void setWidth(int w) {
        this.width = w;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setHeight(int h) {
        this.height = h;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
}
