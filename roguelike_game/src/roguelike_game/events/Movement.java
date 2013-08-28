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
    
    public boolean RUNNING   = true; 
    
    private boolean prevUP = false;
    private boolean prevDOWN = false;
    private boolean prevLEFT = false;
    private boolean prevRIGHT = false;
    
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
        
        if (UP != prevUP) {
            System.out.println("UP changed! UP: " + UP);
            prevUP = UP;
        }
        if (DOWN != prevDOWN) {
            System.out.println("DOWN changed! DOWN: " + DOWN);
            prevDOWN = DOWN;
        }
        if (LEFT != prevLEFT) {
            System.out.println("LEFT changed! LEFT: " + LEFT);
            prevLEFT = LEFT;
        }
        if (RIGHT != prevRIGHT) {
            System.out.println("RIGHT changed! RIGHT: " + RIGHT);
            prevRIGHT = RIGHT;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        key[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        key[ke.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        int code = ke.getKeyCode();
        if(code == KeyEvent.VK_SHIFT) {
            RUNNING = !RUNNING;
            System.out.println("running = " + RUNNING);
        }
    }    
}
