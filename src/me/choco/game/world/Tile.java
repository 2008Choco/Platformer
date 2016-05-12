package me.choco.game.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.choco.game.Game;
import me.choco.game.entity.variants.Collidable;
import me.choco.game.utils.Camera;

public class Tile implements Collidable{
	
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
	
	public void tick(){}
	
	public void render(Graphics g){
		g.drawImage(texture, location.getRawX() - camera.getXOffset(), location.getRawY() - camera.getYOffset(), width, height, null);
	}
	
	@Override
	public String toString() {
		return "Tile:{x:" + location.getX() + ",y:" + location.getY() + ",type:" + type.name() + "}";
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) location.getRawX(), (int) location.getRawY(), width, height);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle((int) location.getRawX() + 5, (int) location.getRawY(), width - 10, 5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) location.getRawX(), (int) location.getRawY() + 1, 5, height - 2);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle((int) (location.getRawX() + width) - 5, (int) location.getRawY() + 1, 5, height - 2);
	}
	
	public Rectangle getBoundsDown(){
		return new Rectangle((int) location.getRawX() + 5, (int) (location.getRawY() + height) - 5, width - 10, 5);
	}
	
	@Override
	public boolean collidesWith(Collidable entity){
		return this.getBounds().intersects(entity.getBounds());
	}
	
	@Override
	public boolean collidesTop(Collidable entity){
		return this.getBoundsTop().intersects(entity.getBounds());
	}
	
	@Override
	public boolean collidesLeft(Collidable entity){
		return this.getBoundsLeft().intersects(entity.getBounds());
	}
	
	@Override
	public boolean collidesRight(Collidable entity){
		return this.getBoundsRight().intersects(entity.getBounds());
	}
	
	@Override
	public boolean collidesDown(Collidable entity){
		return this.getBoundsDown().intersects(entity.getBounds());
	}
}