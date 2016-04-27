package me.choco.game.entity;

import java.awt.Graphics;

import me.choco.game.utils.tilemaps.Location;

public abstract class GameObject {
	
	protected Location location;
	protected ObjectType type;
	
	protected GameObject(int x, int y, ObjectType type){
		this(x, y);
		this.type = type;
	}
	
	protected GameObject(int x, int y){
		this.location = new Location(x, y);
		this.type = ObjectType.UNKNOWN;
	}

	public int getX() {
		return location.getX();
	}

	public void setX(int x) {
		this.location.setX(x);
	}

	public int getY() {
		return location.getY();
	}

	public void setY(int y) {
		this.location.setY(y);
	}
	
	public Location getLocation(){
		return location;
	}

	public ObjectType getType() {
		return type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}