package me.choco.game.utils.tilemaps.generator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.choco.game.utils.tilemaps.Location;

public class Tile {
	
	private boolean loaded = false;
	
	private final TileType type;
	private final Location location;
	private final int width, height;
	private final BufferedImage image;
	public Tile(Location location, int width, int height, TileType type, BufferedImage image){
		this.location = location;
		this.width = width; this.height = height;
		this.type = type;
		this.image = image;
	}
	
	public int getX() {
		return location.getX();
	}
	
	public int getY() {
		return location.getY();
	}
	
	public Location getLocation(){
		return location;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getSprite(){
		return image;
	}
	
	public void setLoaded(boolean loaded){
		this.loaded = loaded;
	}
	
	public boolean isLoaded(){
		return loaded;
	}
	
	public TileType getType(){
		return type;
	}
	
	public void render(Graphics g){
		
	}
}