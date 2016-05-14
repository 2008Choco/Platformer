package me.choco.game.world;

import java.awt.image.BufferedImage;

import me.choco.game.utils.general.resources.Texture;

public enum TileType {
	DIRT(0, true, Texture.GAME_TILE_DIRT.getTexture()),
	GRASS(1, true, Texture.GAME_TILE_GRASS.getTexture()),
	STONE(2, true, Texture.GAME_TILE_STONE.getTexture());
	
	private int id;
	private boolean solid = true;
	private BufferedImage texture;
	TileType(int id, boolean solid, BufferedImage texture){
		this.id = id;
		this.solid = solid;
		this.texture = texture;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isSolid(){
		return solid;
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