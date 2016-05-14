package me.choco.game.utils.general.resources;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private final int spriteCountX, spriteCountY;
	
	private final BufferedImage sheet;
	private final BufferedImage[] sprites;
	private final int spriteWidth, spriteHeight;
	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight){
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		
		this.spriteCountX = (int) Math.floor(sheet.getWidth() / spriteWidth);
		this.spriteCountY = (int) Math.floor(sheet.getHeight() / spriteHeight);
		
		this.sprites = new BufferedImage[spriteCountX * spriteCountY];
		
		// Load the sprites to the BufferedImage array
		int spriteId = 0;
		for (int x = 0; x < spriteCountX; x++){
			for (int y = 0; y < spriteCountY; y++){
				sprites[spriteId] = sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
				spriteId++;
			}
		}
	}
	
	public BufferedImage getSpriteSheet(){
		return sheet;
	}
	
	public BufferedImage getSprite(int id){
		return sprites[id];
	}
	
	public BufferedImage[] getAllSprites() {
		return sprites;
	}
	
	public int getSpriteWidth(){
		return spriteWidth;
	}
	
	public int getSpriteHeight(){
		return spriteHeight;
	}
	
	public int getSpriteCountX(){
		return spriteCountX;
	}
	
	public int getSpriteCountY(){
		return spriteCountY;
	}
}