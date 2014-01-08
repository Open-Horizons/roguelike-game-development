/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author andyafw
 */
public class InventoryPanel extends JPanel {
    private Game game;
    public int inventory_width = 7;
    public int inventory_height = 7;
    public int size = 32;
    public int inventory_start = 352;
    public int armor_start = 220;
    
    public Point[] inventory_pos;
    
    public InventoryPanel(Game game) {
        this.inventory_pos = new Point[10];
        inventory_pos[0] = new Point(3, 0); //helmet
        inventory_pos[1] = new Point(2, 1); //hands
        inventory_pos[2] = new Point(3, 1); //chest armor
        inventory_pos[3] = new Point(4, 1); //belt
        inventory_pos[4] = new Point(1, 2); //ring
        inventory_pos[5] = new Point(3, 2); //pants
        inventory_pos[6] = new Point(5, 2); //left hand sword
        inventory_pos[7] = new Point(1, 3); //amulet
        inventory_pos[8] = new Point(3, 3); //boots
        inventory_pos[9] = new Point(5, 3); //right hand sword
        this.game = game;
        setPreferredSize(new Dimension(226, 600));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //clear sceen
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 226, 600);
        
        //create map
        int msize = 200;
        g.setColor(Color.red);
        g.fillArc(10, 10, msize, msize, 0, 360);
        
        //create armor inventory 
        int next = 0;
        for(int y = 0; y < inventory_height; y++) {
            for (int x = 0; x < inventory_width; x++) {
                if(next < 10) {
                    if(inventory_pos[next].x == x && inventory_pos[next].y == y) {
                        g.setColor(Color.black);
                        g.fillRect(x * size, y * size + armor_start, size, size);
                        g.setColor(Color.green);
                        g.drawRect(x * size, y * size + armor_start, size, size);
                        if(game.player.getInventory().getEquip(next) != null) {
                            g.drawImage(game.player.getInventory().getEquip(next).getSprite().getImage(), x * size, y * size + armor_start, size, size, null);
                        }
                        next++;
                    }
                }
            }
        }
        
        //create player inventory
        for(int y = 0; y < inventory_height; y++) {
            for (int x = 0; x < inventory_width; x++) {
                g.setColor(Color.black);
                g.fillRect(x * size, y * size + inventory_start, size, size);
                g.setColor(Color.yellow);
                g.drawRect(x * size, y * size + inventory_start, size, size);
                if(game.player.getInventory().getItem(x, y) != null) {
                    g.drawImage(game.player.getInventory().getItem(x, y).getSprite().getImage(), x * size, y * size + inventory_start, size, size, null);
                }
            }
        }
    }
}
