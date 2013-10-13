/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import roguelike_game.developer.DeveloperConsole;
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
        public Movement move;
        private TileMap map;
        public Camera cam = new Camera();
        
        public int width = 1100;
        public int height = 600;
        
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
            if(game.mainmenu.menuon) {
                game.mainmenu.paint(g);
            } else {    
                for(int y = 0; y < 19; y++) {
                    for (int x = 0; x < 28; x++) {
                        g.drawImage(Sprite.WALL.getImage(), x * map.size, y * map.size, map.size, map.size, null);
                    }
                }
                game.tilemap.render(g, cam.x, cam.y);
                game.player.render(g, cam.x, cam.y);                
            }
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
            System.out.println("mouse pos " + (move.MOSX + cam.x) / map.size + " , " + ((move.MOSY + cam.y) / map.size - 1));
            System.out.println("player pos " + player.getX() + " , " + player.getY());
        }
        
        public void updatePlayer(Sprite sprite, int x, int y) {
            player.setSprite(sprite);
            player.setX(player.getX() + x);
            player.setY(player.getY() + y);
            cam.x += player.getSize() * x;
            cam.y += player.getSize() * y;
            
            System.out.println("Moving player to " + player.getX() + ", " + player.getY() + ".");
        }
        
        @Override
        public void run() {
            int FPSrate = 1000/game.FPS;
            
            boolean[] wait = {false, false, false, false};
            
            System.out.println("Before Loop");
            
            while (game.running) {
                game.setTitle(game.version);
                game.counter++;
                move.update();

                if(move.OPEN_DEV) {
                    DeveloperConsole.getInstance().setVisible(true);
                    move.OPEN_DEV = false;
                }
                
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
                        
                        int speed = 1;
                        switch (i) {
                        case 0:
                            if (!collision(0, -speed)) {
                                updatePlayer(Sprite.PLAYER_UP, 0, -speed);
                            }
                            break;
                        case 1:
                            if (!collision(0, speed)) {
                                updatePlayer(Sprite.PLAYER_DOWN, 0, speed);
                            }
                            break;
                        case 2:
                            if (!collision(-speed, 0)) {
                                updatePlayer(Sprite.PLAYER_LEFT, -speed, 0);
                            }
                            break;
                        case 3:
                            if (!collision(speed, 0)) {
                                updatePlayer(Sprite.PLAYER_RIGHT, speed, 0);
                            }
                            break;
                        }                        
                        wait[i] = true;
                    }
                }
                
                // nanoTime() to measure refresh rate
                long frameStart = System.nanoTime();
                repaint();
                long frameStop = System.nanoTime();
                long time = frameStop - frameStart;                
                System.out.println(time);
                
                // update();
                
                try {
                    Thread.sleep(FPSrate);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted thread!");
                }
                
                // FPS Counter, prints amount of frames displayed every second
                if ((game.counter % game.FPS) == 0){
                    System.out.println("Frames: " + game.counter);
                    game.version = "Rogue Game - Pre-Alpha build v0.0.1 - frames - " + game.counter;
                    game.counter = 0;
                }
            }
        }
    }
