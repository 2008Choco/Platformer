package wtf.choco.platformer.client.render.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wtf.choco.platformer.client.render.texture.SpriteSheet;

public class Animation {

	private BufferedImage[] sprites;
	private int tickDelay = 1;
	private int spriteWidth, spriteHeight;

	private int currentFrame = 0;

	public Animation(int tickDelay, SpriteSheet sheet){
		this.sprites = sheet.getAllSprites();
		this.tickDelay = tickDelay;

		this.spriteWidth = sheet.getSpriteWidth();
		this.spriteHeight = sheet.getSpriteHeight();
	}

	@Deprecated
	public Animation(int tickDelay, BufferedImage... sprites){
		if (sprites == null)
			throw new IllegalArgumentException("Cannot have null sprites in an animation");

		this.sprites = sprites;
		this.tickDelay = tickDelay;

		this.spriteWidth = sprites[0].getWidth();
		this.spriteHeight = sprites[0].getHeight();
	}

	public void setTickDelay(int delay){
		this.tickDelay = delay;
	}

	public float getTickDelay(){
		return tickDelay;
	}

	public int getCurrentAnimationFrame(){
		return currentFrame;
	}

	private void nextFrame(){
		currentFrame++;
		if (currentFrame >= sprites.length)
			currentFrame = 0;
	}

	private int tickTimer = 0;
	public void render(Graphics g, int x, int y){
		render(g, x, y, false);
	}

	public void render(Graphics g, int x, int y, boolean inverted){
		tickTimer++;
		if (tickTimer >= tickDelay)
			nextFrame();

		// Render current animation frame
		if (sprites[currentFrame] == null) return;

		if (inverted){ g.drawImage(sprites[currentFrame], x + spriteWidth, y, -spriteWidth, spriteHeight, null); }
		else{ g.drawImage(sprites[currentFrame], x, y, spriteWidth, spriteHeight, null); }
	}
}