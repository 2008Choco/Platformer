package me.choco.game.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.choco.game.Game;
import me.choco.game.entity.Entity;
import me.choco.game.utils.Camera;

public class Tile {
	
	private static final Camera camera = Game.getCamera();
	
	private TileType type;
	private final Location location;
	private final int width, height;
	private final BufferedImage texture;
	public Tile(Location location, int width, int height, TileType type){
		this.location = location;
		this.width = width; this.height = height;
		this.type = type;
		this.texture = type.getTexture();
	}
	
	public float getX() {
		return location.getX();
	}
	
	public float getY() {
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
		return texture;
	}
	
	public TileType getTileType(){
		return type;
	}
	
	public void setTileType(TileType type){
		this.type = type;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(location.getRawX(), location.getRawY(), width, height);
	}
	
	public boolean collidesWith(Entity entity){
		return entity.getBounds().intersects(getBounds());
	}
	
	public void tick(){}
	
	public void render(Graphics g){
		g.drawImage(texture, location.getRawX() - camera.getXOffset(), location.getRawY() - camera.getYOffset(), width, height, null);
	}
	
	@Override
	public String toString() {
		return "Tile:{x:" + location.getX() + ",y:" + location.getY() + ",type:" + type.name() + "}";
	}
}