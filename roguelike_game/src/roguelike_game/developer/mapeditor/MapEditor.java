/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.developer.mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author andyafw
 */
public class MapEditor extends JFrame {
    
    private ArrayList<Integer> tilelist;
    
    int width = 0;
    int height = 0;
    int size = 32;
    
    public MapEditor() {
        tilelist = new ArrayList<Integer>();
        readInputFile("./res/maps/map.txt");
        setPreferredSize(new Dimension(10 * size, 10 * size));
    }
    
    private void readInputFile(String name) {
        File file = new File(name);
        BufferedReader read;
        try {
            read = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String s;
            while((s = read.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(s, " ");
                width = tokens.countTokens();
                while(tokens.hasMoreTokens()) {
                    try {
                        int num = Integer.parseInt(s);
                        tilelist.add(num);
                    } catch(NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
                height += 1;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    private class Map extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            int scrollx = 0, scrolly = 0;
            g.setColor(Color.black);
            g.fillRect(0, 0, 10 * size, 10 * size);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Image image;
                    switch(tilelist.get(y * width + x)) {
                        case 0: ; break;
                    }
                    
                    //g.drawImage(image, x * size - scrollx, y * size - scrolly, size, size, null);
                }
            }
        }
    }
}
