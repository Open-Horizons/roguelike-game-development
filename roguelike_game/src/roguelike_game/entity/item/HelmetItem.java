package roguelike_game.entity.item;

import roguelike_game.graphics.Sprite;

public class HelmetItem extends Item {
    protected int health = 0;
    
    public HelmetItem(Sprite sprite, int health) {
        super(Item.Type.HELMET, sprite);
        this.health = health;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int heal) {
        health = heal;
    }
}
