/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author andyafw
 */
public class InventoryPanel extends JPanel {
    private Roguelike_game game;
    public int width = 6;
    public int height = 6;
    public int size = 32;
    public int start = 384;
    
    public InventoryPanel(Roguelike_game game) {
        this.game = game;
        setPreferredSize(new Dimension(200, 600));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 200, 600);
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                g.setColor(Color.black);
                g.fillRect(x * size, y * size + start, size, size);
                g.setColor(Color.yellow);
                g.drawRect(x * size, y * size + start, size, size);
            }
        }
    }
}
