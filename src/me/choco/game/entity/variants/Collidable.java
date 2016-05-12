package me.choco.game.entity.variants;

import java.awt.Rectangle;

public interface Collidable {
	public Rectangle getBounds();
	
	public Rectangle getBoundsTop();
	
	public Rectangle getBoundsLeft();
	
	public Rectangle getBoundsRight();
	
	public Rectangle getBoundsDown();
	
	public boolean collidesWith(Collidable entity);
	
	public boolean collidesTop(Collidable entity);
	
	public boolean collidesLeft(Collidable entity);
	
	public boolean collidesRight(Collidable entity);
	
	public boolean collidesDown(Collidable entity);
}