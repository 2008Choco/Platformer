
package me.choco.game.entity;

import java.awt.Rectangle;

import me.choco.game.entity.variants.Collidable;
import me.choco.game.entity.variants.Direction;
import me.choco.game.world.Location;

public abstract class Entity extends GameObject implements Collidable{
	
	protected int width, height;
	protected float velX = 0, velY = 0;
	protected boolean airborn = true;
	
	protected Direction direction = Direction.NEITHER;
	
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
	
	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public boolean isAirborn(){
		return this.airborn;
	}
	
	public void setAirborn(boolean airborn){
		this.airborn = airborn;
	}
	
	public Direction getDirection(){
		return direction;
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
	
	public boolean collidesWith(Collidable entity){
		return this.getBounds().intersects(entity.getBounds());
	}
	
	public boolean collidesTop(Collidable entity){
		return this.getBoundsTop().intersects(entity.getBounds());
	}
	
	public boolean collidesLeft(Collidable entity){
		return this.getBoundsLeft().intersects(entity.getBounds());
	}
	
	public boolean collidesRight(Collidable entity){
		return this.getBoundsRight().intersects(entity.getBounds());
	}
	
	public boolean collidesDown(Collidable entity){
		return this.getBoundsDown().intersects(entity.getBounds());
	}
}