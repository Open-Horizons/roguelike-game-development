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
	private static final long serialVersionUID = 2819259544532313748L;
	private Game game;
    public int width = 7;
    public int height = 7;
    public int size = 32;
    public int inventory_start = 352;
    public int armor_start = 140;
    
    public Point[] postions = {new Point(3, 1),   /*helmet       0*/ new Point(2, 2),   /*left sword   1*/
    						   new Point(3, 2),   /*chest armor  2*/ new Point(4, 2),   /*right sword  3*/
    						   new Point(3, 3),   /*pants        4*/ new Point(1, 4),   /*amulet       5*/
    						   new Point(3, 4),   /*boots        6*/ new Point(5, 4),   /*gloves       7*/
    						   new Point(1, 5),   /*ring         8*/ new Point(5, 5)};  /*belt         9*/
    						   
    
    public InventoryPanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(226, 600));
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        //clear screen
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 226, 600);
        
        g.setColor(Color.red);
        g.fillArc(0, 0, 226, 140, 0, 360);
        
        //create armor inventory 
        int next = 0;
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(next < 10) {
                    if(postions[next].x == x && postions[next].y == y) {
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
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
