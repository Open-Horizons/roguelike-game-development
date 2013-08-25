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
        private Roguelike_game game;
        private Player player;
        private Movement move;
        private TileMap map;
        
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
        
        public void update() {
            
        }
        
        @Override
        public void run() {
            int FPSrate = 1000/game.FPS;
            
            boolean[] wait = {false, false, false, false};
            while (game.running) {
                move.update();

                for (byte i = 0; i < wait.length; i++) {
                    boolean isPressed = false;
                    switch (i) {
                        case 0:
                            isPressed = move.UP;
                            break;
                        case 1:
                            isPressed = move.DOWN;
                            break;
                        case 2:
                            isPressed = move.LEFT;
                            break;
                        case 3:
                            isPressed = move.RIGHT;
                            break;
                    }
                
                    if (wait[i]) {
                        if (!isPressed) {
                            wait[i] = false;
                        }
                    } else if (isPressed) {
                        Sprite sprite = null;
                        int posX = 0, posY = 0;
                        boolean isMove = false;
                        
                        switch (i) {
                        case 0:
                            if (!collision(0, -1)) {
                                sprite = Sprite.PLAYER_UP;
                                posX = player.getX();
                                posY = player.getY() - 1;   
                                isMove = true;
                            }
                            break;
                        case 1:
                            if (!collision(0, 1)) {
                                sprite = Sprite.PLAYER_DOWN;
                                posX = player.getX();
                                posY = player.getY() + 1;
                                isMove = true;
                            }
                            break;
                        case 2:
                            if (!collision(-1, 0)) {
                                sprite = Sprite.PLAYER_LEFT;
                                posX = player.getX() - 1;
                                posY = player.getY();
                                isMove = true;
                            }
                            break;
                        case 3:
                            if (!collision(1, 0)) {
                                sprite = Sprite.PLAYER_RIGHT;
                                posX = player.getX() + 1;
                                posY = player.getY();
                                isMove = true;
                            }
                            break;
                        }
                        if (isMove) {
                            System.out.println("Moving player to " + posY + ", " + posX + ".");
                            player.setSprite(sprite);
                            player.setY(posY);
                            player.setX(posX);
                        }
                        
                        wait[i] = true;
                    }
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