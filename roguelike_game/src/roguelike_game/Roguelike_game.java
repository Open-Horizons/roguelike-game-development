package roguelike_game;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import roguelike_game.entity.Player;
import roguelike_game.graphics.Sprite;

public class Roguelike_game extends JFrame {
    public TileMap tilemap;
    public Player player;
    
    public Roguelike_game() {
        super("Rogue Game");
        tilemap = new TileMap(this, 34, 18);
        tilemap.randomMap();
        
        player = new Player(10, 10, tilemap.size, Sprite.PLAYER, tilemap);
        
        Painting painting = new Painting(this);
        add(painting);
    }

    private class Painting extends JPanel {

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
    }
    
    public static void main(String[] args) {
        Roguelike_game game = new Roguelike_game();
        game.setSize(1110, 620);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
