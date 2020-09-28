package wtf.choco.platformer.engine.client.texture;

import java.awt.image.BufferedImage;

/**
 * Represents a series of stitched-together identically-sized images with which
 * multiple textures may be created.
 */
public class SpriteSheet {

	private final BufferedImage sheet;
	private final BufferedImage[] sprites;
	private final int spriteWidth, spriteHeight;

	/**
	 * Construct and parse a new sprite sheet based on the given buffered image
	 * and the set dimensions.
	 *
	 * @param sheet the sprite sheet image
	 * @param spriteWidth a single sprite width in pixels
	 * @param spriteHeight a single sprite height in pixels
	 */
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

	/**
	 * Get the sprite sheet.
	 *
	 * @return the sprite sheet
	 */
	public BufferedImage getSpriteSheet() {
		return sheet;
	}

	/**
	 * Get the sprite at the given index.
	 *
	 * @param index the sprite index
	 *
	 * @return the sprite
	 */
	public BufferedImage getSprite(int index) {
		return sprites[index];
	}

	/**
	 * Get an array of all sprites generated from this sprite sheet.
	 *
	 * @return all generated sprites
	 */
	public BufferedImage[] getAllSprites() {
		return sprites;
	}

	/**
	 * Get the width of an individual sprite on this sprite sheet.
	 *
	 * @return the sprite width
	 */
	public int getSpriteWidth() {
		return spriteWidth;
	}

    /**
     * Get the height of an individual sprite on this sprite sheet.
     *
     * @return the sprite height
     */
	public int getSpriteHeight() {
		return spriteHeight;
	}

}