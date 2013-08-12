/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author andyafw
 */
public class Movement implements KeyListener{
    public boolean UP        = false;
    public boolean DOWN      = false;
    public boolean LEFT      = false;
    public boolean RIGHT     = false;
    public boolean INVENTORY = false;
    
    private boolean[] key = new boolean[500];
    
    public Movement() {
        for(int i = 0; i < key.length; i++) {
            key[i] = false;
        }
    }
    
    public void update() {
        UP = key[KeyEvent.VK_UP];
        DOWN = key[KeyEvent.VK_DOWN];
        LEFT = key[KeyEvent.VK_LEFT];
        RIGHT = key[KeyEvent.VK_RIGHT];
        
        //System.out.println("up = " + UP);
        //System.out.println("DOWN = " + DOWN);
        //System.out.println("LEFT = " + LEFT);
        //System.out.println("RIGHT = " + RIGHT);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        key[ke.getKeyCode()] = true;
        System.out.println("key pressed");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        key[ke.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}    
}
