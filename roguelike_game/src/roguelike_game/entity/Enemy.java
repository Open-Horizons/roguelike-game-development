/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

import roguelike_game.Game;
import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
public class Enemy extends Mob {
    
    public Enemy(int x, int y) {
        super(x, y);
        setSprite(Sprite.ALIEN_ENEMY);
    }
    
    public void update(Game game) {
        int r = 0;
        int xx = 0;
        int yy = 0;
        r = 1 + game.rand.nextInt(4);
        if(r == 1) {//go left
            xx = -1;
            yy = 0;
        }
        if(r == 2) {//go down
            xx = 0;
            yy = 1;
        }
        if(r == 3) {//go right
            xx = 1;
            yy = 0;
        }
        if(r == 4) {//go up
            xx = 0;
            yy = -1;
        }
        if(!game.collision(xx, yy, this)) {
            setX(getX() + xx);
            setY(getY() + yy);
        } else {
            //do nothing
        }
    }
}
