/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import roguelike_game.Roguelike_game;

/**
 *
 * @author andyafw
 */
public class Movement implements KeyListener, MouseListener, MouseMotionListener {
    public boolean UP        = false;
    public boolean DOWN      = false;
    public boolean LEFT      = false;
    public boolean RIGHT     = false;
    
    public boolean OPEN_DEV = false;
    public boolean RIGHTCLICK = false;
    
    private boolean prevUP = false;
    private boolean prevDOWN = false;
    private boolean prevLEFT = false;
    private boolean prevRIGHT = false;
    
    public int MOSX = 0;
    public int MOSY = 0;
    
    public int MENU_SELECTION = 0;
    
    private boolean[] key = new boolean[500];
    
    private Roguelike_game game;
    
    public Movement(Roguelike_game game) {
        this.game = game;
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
        if(ke.getKeyCode() < key.length) {
        key[ke.getKeyCode()] = true;
        
            if (ke.getKeyCode() == KeyEvent.VK_BACK_QUOTE) {// && ke.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                System.out.println("tilda " + OPEN_DEV);
                OPEN_DEV = !OPEN_DEV;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        key[ke.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}    

    @Override
    public void mouseClicked(MouseEvent me) {}
    
    @Override
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        int scrollx = game.painting.cam.x;
        int scrolly = game.painting.cam.y;
        int tilex = (mx - scrollx) / game.tilemap.size;
        int tiley = (my - scrolly) / game.tilemap.size - 1;
        if(tilex < game.tilemap.width && tilex >= 0 && tiley < game.tilemap.height && tiley >= 0) {
            if(game.tilemap.items[tiley][tilex] != null) {
                ItemPopupMenu menu = new ItemPopupMenu(game, tilex, tiley);
                menu.show(e.getComponent(), e.getX(), e.getY());
            } else {
                System.out.println("tile is null");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {
        int xx = me.getX() + 2;
        int yy = me.getY() + 2;
        int sizex = 300;
        int sizey = 30;
        Rectangle rect;
        
        if(game.mainmenu.menuon) {
            rect = new Rectangle(290, 300, sizex, sizey);
            if(rect.contains(xx, yy)) {
                MENU_SELECTION = 1;
                return;
            }
            rect = new Rectangle(290, 360, sizex, sizey);
            if(rect.contains(xx, yy)) {
                MENU_SELECTION = 2;
                return;
            }
            rect = new Rectangle(290, 420, sizex, sizey);
            if(rect.contains(xx, yy)) {
                MENU_SELECTION = 3;
            }
        }
    }
}