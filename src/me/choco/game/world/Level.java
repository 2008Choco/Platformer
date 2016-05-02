package me.choco.game.world;

import java.awt.Graphics;

public class Level {
	
	private final Location minLocation, maxLocation;
	private String name;
	
	private final Tile[][] tiles = new Tile[100][100];
	private final Tile[][] viewableTiles = new Tile[10][10];
	
	private final TileGenerator generator;
	public Level(TileGenerator generator, String name, int maxX, int maxY){
		this.generator = generator;
		this.name = name;
		
		this.minLocation = new Location(0, 0);
		this.maxLocation = new Location(maxX, maxY);
	}
	
	public Tile getTileAt(int x, int y){
		Location location = new Location(x, y);
		
		for (Tile[] tiles : this.tiles)
			for (Tile tile : tiles)
				if (tile.getLocation().equals(location)) return tile;
		return null;
	}
	
	public TileGenerator getTileGenerator(){
		return generator;
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
	
	public void tick(){}
	
	public void render(Graphics g){
		for (Tile[] tiles : this.viewableTiles){
			for (Tile tile : tiles){
				tile.render(g);
			}
		}
	}
}