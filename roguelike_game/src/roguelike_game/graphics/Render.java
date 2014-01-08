/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.graphics;

import java.awt.Color;
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
    public int inventory_width = 7;
    public int inventory_height = 7;
    public int inventory_start = 352;
    public Point[] inventory_pos;
    
    public int armor_start = 220;
    
    public int map_width = 100;
    public int map_height = 100;
    public int res_width = 1100;
    public int res_height = 600;
    
    private Game game;
    
    public Render(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1326, 600));
        setInventoryPoints();
    }
    
    private void setInventoryPoints() {
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
        int limitx = Math.min((scrollx + res_width) / 30 + 1, map_width);
        int limity = Math.min((scrolly + res_height) / 30 + 1, map_height);
        drawBackground(g);
        if(!game.move.ISO_VIEW) {
            draw2DTilemap(g, startx, starty, limitx, limity, scrollx, scrolly);
            draw2DItems(g, startx, starty, limitx, limity, scrollx, scrolly);
            draw2DEnemies(g, scrollx, scrolly);
            draw2DPlayer(g, scrollx, scrolly);
        } else {
            drawIsoTilemap(g, startx, starty, limitx, limity, scrollx, scrolly);
            drawIsoItems(g, startx, starty, limitx, limity, scrollx, scrolly);
            drawIsoPlayer(g, scrollx, scrolly);
        }
        if(game.move.OPEN_INVENTORY) {
            drawInventory(g);
        }
    }
    
    public void drawInventory(Graphics g) {
        //inventory location
        int xx = getWidth() - 226;
        
       //create map
        int msize = 200;
        g.setColor(Color.red);
        g.drawArc(10 + xx, 10, msize, msize, 0, 360);
        
        //create armor inventory 
        int next = 0;
        for(int y = 0; y < inventory_height; y++) {
            for (int x = 0; x < inventory_width; x++) {
                if(next < 10) {
                    if(inventory_pos[next].x == x && inventory_pos[next].y == y) {
                        //g.setColor(Color.black);
                        //g.fillRect(x * size, y * size + armor_start, size, size);
                        g.setColor(Color.green);
                        g.drawRect(x * size + xx, y * size + armor_start, size, size);
                        if(game.player.getInventory().getEquip(next) != null) {
                            g.drawImage(game.player.getInventory().getEquip(next).getSprite().getImage(), x * size + xx, y * size + armor_start, size, size, null);
                        }
                        next++;
                    }
                }
            }
        }
        
        //create player inventory
        for(int y = 0; y < inventory_height; y++) {
            for (int x = 0; x < inventory_width; x++) {
                //g.setColor(Color.black);
                //g.fillRect(x * size, y * size + inventory_start, size, size);
                g.setColor(Color.yellow);
                g.drawRect(x * size + xx, y * size + inventory_start, size, size);
                if(game.player.getInventory().getItem(x, y) != null) {
                    g.drawImage(game.player.getInventory().getItem(x, y).getSprite().getImage(), x * size + xx, y * size + inventory_start, size, size, null);
                }
            }
        }
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
            for (int x = 0; x < 42; x++) {
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