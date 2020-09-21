package wtf.choco.platformer.client.render.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wtf.choco.platformer.engine.client.texture.SpriteSheet;

public class Animation {

	private int spriteIndex = 0;
    private int frameTimer = 0;

	private final BufferedImage[] sprites;
    private final int spriteWidth, spriteHeight;
    private final int frameInterval;

	public Animation(int frameInterval, SpriteSheet sheet) {
		this.sprites = sheet.getAllSprites();
		for (BufferedImage sprite : sprites) {
		    if (sprite == null) {
		        throw new IllegalArgumentException("Sprite sheet container null sprite. Failed to parse?");
		    }
		}

		this.spriteWidth = sheet.getSpriteWidth();
		this.spriteHeight = sheet.getSpriteHeight();
		this.frameInterval = Math.max(1, frameInterval);
	}

	public float getTickDelay() {
		return frameInterval;
	}

	private void nextFrame() {
		if (++spriteIndex >= sprites.length) {
			this.spriteIndex = 0;
		}
	}

	public void render(Graphics graphics, int x, int y) {
		this.frameTimer++;
		if (frameTimer >= frameInterval) {
			this.nextFrame();
		}

		graphics.drawImage(sprites[spriteIndex], x, y, spriteWidth, spriteHeight, null);
	}

}