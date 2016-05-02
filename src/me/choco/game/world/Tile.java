package me.choco.game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.choco.game.entity.GameObject;
import me.choco.game.entity.ObjectType;

public class Tile extends GameObject{
	
	private boolean loaded = false;
	
	private final TileType type;
	private final Location location;
	private final int width, height;
	private final BufferedImage image;
	public Tile(Location location, int width, int height, TileType type){
		super(location, ObjectType.TILE);
		this.location = location;
		this.width = width; this.height = height;
		this.type = type;
		this.image = type.getTexture();
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
	
	public TileType getTileType(){
		return type;
	}
	
	public void tick(){}
	
	public void render(Graphics g){
		g.drawImage(image, location.getX(), location.getY(), width, height, null);
	}
}