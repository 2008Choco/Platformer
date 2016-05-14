package me.choco.game.utils.general.resources;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	@SuppressWarnings("unused")
	private BufferedImage[] sprites;
	private float speed = 1;
	@SuppressWarnings("unused")
	private int spriteWidth, spriteHeight;
	
	private int currentFrame = 0;
	
	public Animation(float speed, SpriteSheet sheet){
		this.sprites = sheet.getAllSprites();
		this.speed = speed;
		
		this.spriteWidth = sheet.getSpriteWidth();
		this.spriteHeight = sheet.getSpriteHeight();
	}
	
	@Deprecated
	public Animation(float speed, BufferedImage... sprites){
		if (sprites == null)
			throw new IllegalArgumentException("Cannot have null sprites in an animation");
		
		this.sprites = sprites;
		this.speed = speed;
		
		this.spriteWidth = sprites[0].getWidth();
		this.spriteHeight = sprites[0].getHeight();
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public int getCurrentAnimationFrame(){
		return currentFrame;
	}
	
	public void drawNextAnimation(Graphics g, int x, int y){
		/* TODO
		 * g.drawImage(sprites[currentFrame], x, y, spriteWidth, spriteHeight, null);
		 * 
		 * Continuously change the currentFrame integer field dependent on the speed variable... somehow?
		 */
	}
}