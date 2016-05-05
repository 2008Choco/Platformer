package me.choco.game.world;

import java.awt.image.BufferedImage;

public enum TileType {
	PASSABLE(0, null),
	BASIC(1, null);
	
	private int id;
	private BufferedImage texture;
	TileType(int id, BufferedImage texture){
		this.id = id;
		this.texture = texture;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	public int getId(){
		return id;
	}
}