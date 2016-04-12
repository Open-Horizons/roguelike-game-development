package roguelike_game.entity.item;

import roguelike_game.graphics.Sprite;

public class ArmorItem extends Item {
    protected int health = 0;
    
    public ArmorItem(Sprite sprite, int health) {
        super(Item.Type.WEAPON, sprite);
        this.health = health;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int heal) {
        health = heal;
    }
}
