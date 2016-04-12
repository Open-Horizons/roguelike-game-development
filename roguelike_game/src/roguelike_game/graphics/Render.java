/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;
import roguelike_game.Game;

/**
 *
 * @author andyafw
 * @version 04/07/2016
 */
public class Render extends JPanel {
	private static final long serialVersionUID = 9189866751182256813L;
	public int size = 32;
    public int width = 100;
    public int height = 100;
    public int res_width = 1100;
    public int res_height = 600;
    
    private Game game;
    
    public Render(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1100, 600));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(game.mainmenu.menuon) {
            game.mainmenu.paint(g);
        } else {    
            render(g, game.cam.x, game.cam.y);
        }
    }
    
    public void render(Graphics g, int scrollx, int scrolly) {
        int startx = Math.max(scrollx / size, 0);
        int starty = Math.max(scrolly / size, 0);
        int limitx = Math.min((scrollx + res_width) / 30 + 1, width);
        int limity = Math.min((scrolly + res_height) / 30 + 1, height);
        drawBackground(g);
        drawScreen(g, startx, starty, limitx, limity, scrollx, scrolly);
    }
    
    public void drawBackground(Graphics g) {
        for(int y = 0; y < 19; y++) {
            for (int x = 0; x < 28; x++) {
                g.drawImage(Sprite.WALL.getImage(), x * size, y * size, size, size, null);
            }
        }
    }
    
    public void drawScreen(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                //draw tilemap
            	g.drawImage(findImage(game.tilemap.tiles[y][x]), x * size - scrollx, y * size - scrolly, size, size, null);
                
                //draw items
                if(game.tilemap.items[y][x] == null) {
                    continue;
                } else {
                    Point p = new Point(x * size, y * size);
                    g.drawImage(game.tilemap.items[y][x].getSprite().getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
                }   
            }
        }
        
        //draw player
        Point p = new Point(game.player.getX() * size, game.player.getY() * size);
        g.drawImage(game.player.getSprite().getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
    }
    
    private Image findImage(int i) {
        switch(i) {
            case 0:
                return Sprite.WALL.getImage();
            default:
                //if don't know number inputed then send floor image
                return Sprite.FLOOR.getImage();
        }
    }
}
