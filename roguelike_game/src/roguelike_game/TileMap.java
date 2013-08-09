package roguelike_game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author andyafw
 */
public class TileMap extends JFrame {
    public TileMap() {
        Draw draw = new Draw();
        add(draw);
    }
    
    public static void main(String[] args) {
        TileMap game = new TileMap();
        game.setSize(1100, 600);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
    
    private class Draw extends JPanel {
            public Draw() {
                this.setPreferredSize(new Dimension(1100, 600));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for(int y = 0; y < 30; y++){
                    for(int x = 0; x < 30; x++) {
                        g.drawRect(x * 20, y * 20, 20, 20);
                    }
                }

            }
    }
}
