package me.choco.game.world;

import java.awt.image.BufferedImage;

public enum TileType {
	BASIC(null),
	TOP(null);
	
	private BufferedImage texture;
	TileType(BufferedImage texture){
		this.texture = texture;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
}