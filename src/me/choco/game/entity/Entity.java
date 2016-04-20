package me.choco.game.entity;

import java.awt.Rectangle;

public abstract class Entity extends GameObject{
	
	protected int width, height;
	protected int velX = 0, velY = 0;
	
	protected Entity(int x, int y, int width, int height, ObjectType type) {
		super(x, y, type);
		this.width = width;
		this.height = height;
	}
	
	protected Entity(int x, int y, int width, int height) {
		super(x, y);
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
		return new Rectangle(x, y, width, height);
	}
	
	public boolean collidesWith(Entity entity){
		return this.getBounds().intersects(entity.getBounds());
	}
}