package roguelike_game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import roguelike_game.entity.Player;
import roguelike_game.events.Movement;
import roguelike_game.graphics.Sprite;

public class Roguelike_game extends JFrame {
    public TileMap tilemap;
    public Player player;
    public Movement move;
    public boolean running = false;
    public String version = "Rogue Game - Pre-Alpha build v0.0.1";
    private Painting painting;
    public int FPS = 60;
    
    public Roguelike_game() {
        tilemap = new TileMap(this, 34, 18);
        tilemap.randomMap();
        move = new Movement();
        
        player = new Player(10, 10, tilemap.size, Sprite.PLAYER_UP, tilemap);
        
        painting = new Painting(this);
        this.addKeyListener(move);
        
        add(painting);
        
        running = true;
        
        Thread thread = new Thread(painting);
        thread.start();
    }    

    private class Painting extends JPanel implements Runnable {
        Roguelike_game game;

        public Painting(Roguelike_game game) {
            this.game = game;
            this.setPreferredSize(new Dimension(1100, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            game.tilemap.render(g);
            game.player.render(g);
        }
        
        public boolean collision(int x, int y) {
            if ((player.getX() + x) < 0 || (player.getX() + x) >= game.tilemap.width || (player.getY() + y) < 0 || (player.getY() + y) >= tilemap.height) {
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
            game.setTitle(version);
            int FPSrate = 1000/FPS;
            
            boolean[] wait = {false, false, false, false};
            while (running) {
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
    
    public static void main(String[] args) {
        Roguelike_game game = new Roguelike_game();
        game.setSize(1117, 620);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setTitle(game.version);
        game.setVisible(true);
    }
}
