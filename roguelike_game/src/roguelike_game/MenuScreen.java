/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author andyafw
 */
public class MenuScreen {
    private Roguelike_game game;
    public boolean menuon = false;
    
    public MenuScreen(Roguelike_game game) {
        this.game = game;
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.green);
        g.drawString("OPEN HORIZONS", 260, 200);
        
        g.setColor(Color.red);
        g.drawString("Main Menu", 310, 260);
        
        font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        g.setFont(font);
        int sizex = 300;
        int sizey = 30;
        g.setColor(Color.red);
        if(game.painting.move.MENU_SELECTION == 1) {
            g.setColor(Color.green);
            g.drawRect(290, 300, sizex, sizey);
        }
        g.drawString("Start Game", 310, 320);
        
        g.setColor(Color.red);
        if(game.painting.move.MENU_SELECTION == 2) {
            g.setColor(Color.green);
            g.drawRect(290, 360, sizex, sizey);
        }
        g.drawString("Game Options", 310, 380);
        
        g.setColor(Color.red);
        if(game.painting.move.MENU_SELECTION == 3) {
            g.setColor(Color.green);
            g.drawRect(290, 420, sizex, sizey);
        }
        g.drawString("Exit", 310, 440);
    }
}