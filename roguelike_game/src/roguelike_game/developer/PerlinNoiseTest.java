/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.developer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import roguelike_game.graphics.PerlinNoise;

/**
 *
 * @author andyafw
 */
public class PerlinNoiseTest extends JFrame{
    private int width = 200;
    private int height = 200;
    private double[][] tiles = new double[height][width];
    public PerlinNoiseTest() {
        setSize(width, height);
        createLandscape();
        Drawing draw = new Drawing();
        add(draw, BorderLayout.CENTER);
    }
    
    private void createLandscape() {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = PerlinNoise.PerlinNoise2D(2, x, y);
            }
        }
    }
    
    private Color checkColor(double p) {
        System.out.println("p = " + p);
        if(p > 0.5) {
            return Color.black;
        } else {
            return Color.white;
        }
    }
    
    private class Drawing extends JPanel {
        public Drawing() {
            setPreferredSize(new Dimension(width, height));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            g.fillRect(0, 0, width, height);
            
            for (int y = 0; y < tiles.length; y++) {
                for (int x = 0; x < tiles[y].length; x++) {
                    g.setColor(checkColor(tiles[y][x]));
                    g.drawRect(x, y, 1, 1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PerlinNoiseTest perlin = new PerlinNoiseTest();
        perlin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perlin.setVisible(true);
    }
}
