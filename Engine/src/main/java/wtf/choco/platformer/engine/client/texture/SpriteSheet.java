package wtf.choco.platformer.engine.client.texture;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private final BufferedImage sheet;
	private final BufferedImage[] sprites;
	private final int spriteWidth, spriteHeight;

	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight) {
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		int spriteCountX = (int) Math.floor(sheet.getWidth() / spriteWidth);
		int spriteCountY = (int) Math.floor(sheet.getHeight() / spriteHeight);

		this.sprites = new BufferedImage[spriteCountX * spriteCountY];

		// Load the sprites to the BufferedImage array
		int spriteId = 0;
		for (int x = 0; x < spriteCountX; x++){
			for (int y = 0; y < spriteCountY; y++){
				this.sprites[spriteId] = sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
				spriteId++;
			}
		}
	}

	public BufferedImage getSpriteSheet() {
		return sheet;
	}

	public BufferedImage getSprite(int id) {
		return sprites[id];
	}

	public BufferedImage[] getAllSprites() {
		return sprites;
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

}