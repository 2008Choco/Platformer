package me.choco.game.world;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Level {
	
	private final Location minLocation, maxLocation;
	private String name;
	private final int tileSize;
	
	private final Map<Location, Tile> tiles = new HashMap<>();
	
	public Level(String name, int maxX, int maxY, int tileSize){
		this.name = name;
		this.tileSize = tileSize;
		
		this.minLocation = new Location(0, 0);
		this.maxLocation = new Location(maxX, maxY);
	}
	
	public Tile getTileAt(Location location){
		return tiles.get(location);
	}
	
	public void setTile(Location location, TileType type){
		if (tiles.get(location) == null){
			tiles.put(location, new Tile(location, tileSize, tileSize, type));
		}else{
			tiles.get(location);
		}
	}
	
	public Location getMinLocation(){
		return minLocation;
	}
	
	public Location getMaxLocation(){
		return maxLocation;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getTileSize(){
		return tileSize;
	}
	
	public Collection<Tile> getTiles(){
		return tiles.values();
	}
	
	public void tick(){
		for (Tile tile : tiles.values())
			tile.tick();
	}
	
	public void render(Graphics g){
		for (Tile tile : tiles.values())
			tile.render(g);
	}
}