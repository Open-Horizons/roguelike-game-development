package roguelike_game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	public static SpriteSheet CHARACTERS = new SpriteSheet("/sprites/character.png");
	
	private BufferedImage bs;
	
	public SpriteSheet(String filename) {
		try {
			bs = ImageIO.read(SpriteSheet.class.getResource(filename));
		} catch (IOException e) {
			System.out.println("spritesheet can not be read in");
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return bs;
	}
}
