package me.choco.game.entity;

import java.awt.Graphics;

import me.choco.game.world.Location;

public abstract class GameObject {
	
	protected Location location;
	protected ObjectType type;
	
	protected GameObject(Location location){
		this.location = location;
		this.type = ObjectType.UNKNOWN;
	}
	
	protected GameObject(Location location, ObjectType type){
		this(location);
		this.type = type;
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