package me.choco.game.entity;

import java.awt.Rectangle;

import me.choco.game.world.Location;

public abstract class Entity extends GameObject{
	
	protected int width, height;
	protected int velX = 0, velY = 0;
	
	protected Entity(Location location, int width, int height, ObjectType type) {
		super(location, type);
		this.width = width;
		this.height = height;
	}
	
	protected Entity(Location location, int width, int height) {
		super(location);
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Rectangle getBounds(){
		return new Rectangle(getX(), getY(), width, height);
	}
	
	public boolean collidesWith(Entity entity){
		return this.getBounds().intersects(entity.getBounds());
	}
}