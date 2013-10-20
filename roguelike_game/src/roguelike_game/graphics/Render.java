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
 */
public class Render extends JPanel {
    public int size = 32;
    public int width = 100;
    public int height = 100;
    
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
        int limitx = Math.min((scrollx + width) / 30 + 1, width);
        int limity = Math.min((scrolly + height) / 30 + 1, height);
        drawBackground(g);
        draw2dTilemap(g, startx, starty, limitx, limity, scrollx, scrolly);
        drawItems(g, startx, starty, limitx, limity, scrollx, scrolly);
        drawPlayer(g, scrollx, scrolly);
    }
    
    public void drawBackground(Graphics g) {
        for(int y = 0; y < 19; y++) {
            for (int x = 0; x < 28; x++) {
                g.drawImage(Sprite.WALL.getImage(), x * size, y * size, size, size, null);
            }
        }
    }
    
    public void drawPlayer(Graphics g, int scrollx, int scrolly) {
        g.drawImage(game.player.getSprite().getImage(), game.player.getX() * size - scrollx, game.player.getY() * size - scrolly, size, size, null);
    }
    
    public void drawIsoTilemap(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                if (x % 2 == 0) {
                    g.drawImage(findImage(game.tilemap.tiles[y][x]), x * size - scrollx, y * size - scrolly, size, size, null);
                }
                else {
                    g.drawImage(findImage(game.tilemap.tiles[y][x]), x * size - scrollx/2, y * size - scrolly, size, size, null);
                }
            }
        }
    }
    
    public void drawIsoTilemap2(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                double halfwidth = 0.5;
                /**
                 * isoX = y/2 + x/2
                 * isoY = y/2 - x/2
                 **/
                Point tempPt = new Point(0, 0);
                if (x % 2 == 0) {
                    tempPt.x = (y * size) + (x * size / 2);    
                    tempPt.y = (y * size) - (x * size / 2);  
                }
                else {
                    tempPt.x = (y * size / 2) + (x * size);    
                    tempPt.y = (y * size / 2) - (x * size); 
                }
                g.drawImage(findImage(game.tilemap.tiles[y][x]), tempPt.x - scrollx, tempPt.y - scrolly, size, size, null);
            }
        }
    }
    public void draw2dTilemap(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                    g.drawImage(findImage(game.tilemap.tiles[y][x]), x * size - scrollx, y * size - scrolly, size, size, null);
            }
        }
    }
    
    public void drawItems(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                if(game.tilemap.items[y][x] == null) {
                    continue;
                } else {
                    g.drawImage(game.tilemap.items[y][x].getSprite().getImage(), x * size - scrollx, y * size - scrolly, size, size, null);
                }
            }
        }          
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
