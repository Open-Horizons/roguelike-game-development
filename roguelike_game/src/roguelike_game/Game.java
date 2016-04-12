package roguelike_game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import roguelike_game.developer.DeveloperConsole;
import roguelike_game.entity.Player;
import roguelike_game.events.Movement;
import roguelike_game.graphics.Render;
import roguelike_game.graphics.Sprite;

public class Game extends JFrame implements Runnable {
	private static final long serialVersionUID = -8786702884271086916L;
	public TileMap tilemap;
    public Player player;
    public Movement move;
    public Camera cam;
    public Render render;
    public InventoryPanel inventorypane;
    public MenuScreen mainmenu;
    
    public int FPS = 100;
    public int counter = 0;
    public boolean running = false;
    public String version = "Rogue Game - Pre-Alpha build v16.04.12";
    
	public Game() {
	    tilemap = new TileMap(100, 100);
	    tilemap.createRandomMap();
	    move = new Movement(this);
	    cam = new Camera();
	    render = new Render(this);
	    player = new Player(13, 9, Sprite.PLAYER_UP);
	    
	    inventorypane = new InventoryPanel(this);
	    mainmenu = new MenuScreen(this);
	    addKeyListener(move);
	    addMouseListener(move);
	    addMouseMotionListener(move);
	    
	    add(render, BorderLayout.CENTER);
	    add(inventorypane, BorderLayout.EAST);
	}
	
	//if no collision then return true
	public boolean collision(int x, int y) {
	    if ((player.getX() + x) < 0 
	        || (player.getX() + x) >= tilemap.width 
	        || (player.getY() + y) < 0 
	        || (player.getY() + y) >= tilemap.height) {
	        return true;
	    }
	    
	    if (tilemap.tiles[player.getY() + y][player.getX() + x] == 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
        
	//update the players movement
	public void updatePlayer(Sprite sprite, int x, int y) {
	    player.setSprite(sprite);
	    player.setX(player.getX() + x);
	    player.setY(player.getY() + y);
	    System.out.println("render " + render.size);
	    cam.x += render.size * x;
	    cam.y += render.size * y;
	    
	    System.out.println("Moving player to " + player.getX() + ", " + player.getY() + ".");
	}
        
    @Override
    public void run() {
        int FPSrate = 1000/FPS;
        
        boolean[] wait = {false, false, false, false};
        
        System.out.println("Before Loop");
        
        while (running) {
            setTitle(version);
            counter++;
            move.update();

            if(move.OPEN_DEV) {
                DeveloperConsole.getInstance().setVisible(true);
                move.OPEN_DEV = false;
            }
            
            // nanoTime() to measure refresh rate
            long frameStart = System.nanoTime();
            repaint();
            long frameStop = System.nanoTime();
            long time = frameStop - frameStart;
            
            update(wait);
            
            try {
                Thread.sleep(FPSrate);
            } catch (InterruptedException e) {
                System.out.println("Interrupted thread!");
            }
            
            // FPS Counter, prints amount of frames displayed every second
            if ((counter % FPS) == 0){
                System.out.println("Frames: " + counter);
                version = "Rogue Game - Pre-Alpha build v0.0.1 - frames - " + counter;
                counter = 0;
            }
        }
    }
    
    public void update(boolean[] wait) {
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
                case 0: checkCollision(Sprite.PLAYER_UP, 0, -speed);break;
                case 1: checkCollision(Sprite.PLAYER_DOWN, 0, speed); break;
                case 2: checkCollision(Sprite.PLAYER_LEFT, -speed, 0); break;
                case 3: checkCollision(Sprite.PLAYER_RIGHT, speed, 0); break;
                }                        
                wait[i] = true;
            }
        }
    }
    
    public void checkCollision(Sprite sprite, int x, int y) {
        if (!collision(x, y)) {//right
            updatePlayer(sprite, x, y);
        }
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.setSize(1117, 620);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setTitle(game.version); 
        game.setVisible(true);
        
        Thread thread = new Thread(game);
        thread.start();
        game.running = true;
    }
}