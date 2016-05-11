package me.choco.game.world;

import java.awt.image.BufferedImage;

import me.choco.game.utils.general.resources.Texture;

public enum TileType {
	DIRT(0, Texture.GAME_TILE_DIRT.getTexture()),
	GRASS(1, null),
	STONE(2, null);
	
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
	
	public static TileType getById(int id){
		for (TileType type : values())
			if (type.getId() == id) return type;
		return null;
	}
	
	public static TileType getByName(String name){
		for (TileType type : values())
			if (type.name().equalsIgnoreCase(name)) return type;
		return null;
	}
}