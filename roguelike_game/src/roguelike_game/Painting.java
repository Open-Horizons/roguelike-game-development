/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import roguelike_game.entity.Player;
import roguelike_game.events.Movement;
import roguelike_game.graphics.Sprite;

/**
 *
 * @author andyafw
 */
    public class Painting extends JPanel implements Runnable {
        Roguelike_game game;
        Player player;
        Movement move;
        TileMap map;
        public Painting(Roguelike_game game) {
            this.game = game;
            player = game.player;
            move = game.move;
            map = game.tilemap;
            setPreferredSize(new Dimension(1100, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            game.tilemap.render(g);
            game.player.render(g);
        }
        
        public boolean collision(int x, int y) {
            if ((player.getX() + x) < 0 || (player.getX() + x) >= game.tilemap.width || (player.getY() + y) < 0 || (player.getY() + y) >= map.height) {
                return true;
            }
            
            if (game.tilemap.tiles[player.getY() + y][player.getX() + x] == 0) {
                return true;
            } else {
                return false;
            }
        }
        
        @Override
        public void run() {
            int FPSrate = 1000/game.FPS;
            
            boolean isPressed = false;
            boolean wait = false;
            while(game.running) {
                move.update();
                
                isPressed = (move.UP || move.DOWN || move.LEFT || move.RIGHT);
                
                if (wait) {
                    if (!isPressed) {
                        wait = false;
                    }
                } else if (isPressed) {
                    if (move.UP && !collision(0, -1)) {
                        player.setSprite(Sprite.PLAYER_UP);
                        player.setY(player.getY() - 1);
                    } else if (move.DOWN && !collision(0, 1)) {
                        player.setSprite(Sprite.PLAYER_DOWN);
                        player.setY(player.getY() + 1);
                    } else if (move.LEFT && !collision(-1, 0)) {
                        player.setSprite(Sprite.PLAYER_LEFT);
                        player.setX(player.getX() - 1);
                    } else if (move.RIGHT && !collision(1, 0)) {
                        player.setSprite(Sprite.PLAYER_RIGHT);
                        player.setX(player.getX() + 1);
                    }
                    
                    wait = true;
                }
                
                repaint();
                
                try {
                    Thread.sleep(FPSrate);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted thread!");
                }
            }
        }
    }