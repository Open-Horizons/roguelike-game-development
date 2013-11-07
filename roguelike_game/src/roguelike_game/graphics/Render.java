/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.Math;
import javax.swing.JPanel;
import roguelike_game.Game;
import roguelike_game.entity.Enemy;

/**
 *
 * @author andyafw
 */
public class Render extends JPanel {
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
        /*
        draw2DTilemap(g, startx, starty, limitx, limity, scrollx, scrolly);
        draw2DItems(g, startx, starty, limitx, limity, scrollx, scrolly);
        draw2DEnemies(g, scrollx, scrolly);
        draw2DPlayer(g, scrollx, scrolly);
        */
        drawIsoTilemap(g, startx, starty, limitx, limity, scrollx, scrolly);
        drawIsoItems(g, startx, starty, limitx, limity, scrollx, scrolly);
        drawIsoPlayer(g, scrollx, scrolly);
    }
    
    public Point isoFormula(int x, int y) {
        int xx = ((y - x) * 16) + 400;
        int yy = ((y + x) * 16) + 20;
        return new Point(xx, yy);
    }
    
    public Point twoFormula(int x, int y) {
        int xx = x * size;
        int yy = y * size;
        return new Point(xx, yy);
    }
    
    public void drawBackground(Graphics g) {
        for(int y = 0; y < 19; y++) {
            for (int x = 0; x < 28; x++) {
                g.drawImage(Sprite.WALL.getImage(), x * size, y * size, size, size, null);
            }
        }
    }

    public void drawIsoTilemap(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {     
    	for (int y = starty; y < limity; y++) {
    		for (int x = startx; x < limitx; x++) {
    			int isoX = (int)((x - y)* 32 * 0.5);
    			int isoY = (int)((x + y) * 32 * 0.25);
    			g.drawImage(findIsoImage(game.tilemap.tiles[y][x]), isoX - scrollx, isoY - scrolly, size, size, null);
    		}
    	}
    } 
    
    public void drawIsoPlayer(Graphics g, int scrollx, int scrolly) {
    	int x = game.player.getX();
    	int y = game.player.getY();
    	int isoX = (int)((x - y)* 32 * 0.5);
		int isoY = (int)((x + y) * 32 * 0.25);
        //Point p = isoFormula(game.player.getX(), game.player.getY());
        g.drawImage(game.player.getSprite().getImage(), isoX + scrollx, isoY - scrolly, size, size, null);
    }  
    
    public void drawIsoItems(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                if(game.tilemap.items[y][x] == null) {
                    continue;
                } else {
                	int isoX = (int)((x - y)* 32 * 0.5);
        			int isoY = (int)((x + y) * 32 * 0.25);
                    //Point p = isoFormula(x, y);
                    g.drawImage(game.tilemap.items[y][x].getSprite().getImage(), isoX - scrollx, isoY - scrolly, size, size, null);
                }
            }
        }          
    }
    
    public void draw2DPlayer(Graphics g, int scrollx, int scrolly) {
        Point p = twoFormula(game.player.getX(), game.player.getY());
        g.drawImage(game.player.getSprite().getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
    }
    
    public void draw2DEnemies(Graphics g, int scrollx, int scrolly) {
        for(Enemy enemy : game.enemyList) {
            Point p = twoFormula(enemy.getX(), enemy.getY());
            g.drawImage(Sprite.UNICON.getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
            
        }
    }
    
    public void draw2DTilemap(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                Point p = twoFormula(x, y);
                g.drawImage(find2DImage(game.tilemap.tiles[y][x]), p.x - scrollx, p.y - scrolly, size, size, null);

            }
        }
    }
    
    public void draw2DItems(Graphics g, int startx, int starty, int limitx, int limity, int scrollx, int scrolly) {
        for(int y = starty; y < limity; y++) {
            for(int x = startx; x < limitx; x++) {
                if(game.tilemap.items[y][x] == null) {
                    continue;
                } else {
                    Point p = twoFormula(x, y);
                    g.drawImage(game.tilemap.items[y][x].getSprite().getImage(), p.x - scrollx, p.y - scrolly, size, size, null);
                }
            }
        }          
    }
    
    private Image findIsoImage(int i) {
        switch(i) {
            case 0:
                return Sprite.ISOWALL.getImage();
            default:
                //if don't know number inputed then send floor image
                return Sprite.ISOFLOOR.getImage();
        }
    }
    
    private Image find2DImage(int i) {
        switch(i) {
            case 0:
                return Sprite.WALL.getImage();
            default:
                //if don't know number inputed then send floor image
                return Sprite.FLOOR.getImage();
        }
    }
}