package roguelike_game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
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
    private InventoryPanel inventorypane;
    public int FPS = 60;
    public int counter = 0;
    public Roguelike_game() {
        tilemap = new TileMap(this, 100, 100);
        tilemap.randomMap();
        move = new Movement();
        
        player = new Player(13, 9, tilemap.size, Sprite.PLAYER_UP, tilemap);
        
        inventorypane = new InventoryPanel(this);
        painting = new Painting(this);
        addKeyListener(move);
        addMouseListener(move);
        addMouseMotionListener(move);
        
        add(painting, BorderLayout.CENTER);
        add(inventorypane, BorderLayout.EAST);
        running = true;
        
        Thread thread = new Thread(painting);
        thread.start();
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
