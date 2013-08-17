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
    
    private Painting painting;
    
    public Roguelike_game() {
        super("Rogue Game");
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
            if((player.getX() + x) < 0 || (player.getX() + x) >= game.tilemap.width || (player.getY() + y) < 0 || (player.getY() + y) >= tilemap.height) {
                return true;
            }
            
            if(game.tilemap.tiles[player.getY() + y][player.getX() + x] == 0) {
                return true;
            } else {
                return false;
            }
        }
        
        @Override
        public void run() {
            int wait = 0;
            while(running) {
                move.update();
                if(wait == 1000000000) {
                    if(move.UP && !collision(0, -1)) {
                        player.setSprite(Sprite.PLAYER_UP);
                        player.setY(player.getY() - 1);
                    }
                    if(move.DOWN && !collision(0, 1)) {
                        player.setSprite(Sprite.PLAYER_DOWN);
                        player.setY(player.getY() + 1);
                    }
                    if(move.LEFT && !collision(-1, 0)) {
                        player.setSprite(Sprite.PLAYER_LEFT);
                        player.setX(player.getX() - 1);
                    }
                    if(move.RIGHT && !collision(1, 0)) {
                        player.setSprite(Sprite.PLAYER_RIGHT);
                        player.setX(player.getX() + 1);
                    }
                    repaint();
                    wait = 0;
                } else {
                    wait++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Roguelike_game game = new Roguelike_game();
        game.setSize(1110, 620);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
