package me.choco.game.entity;

import java.awt.Graphics;
import java.util.Random;

import me.choco.game.Game;
import me.choco.game.utils.Camera;
import me.choco.game.world.Location;

public abstract class GameObject {
	
	protected static final Camera camera = Game.getCamera();
	protected static final Random random = new Random();
	
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
	
	public Location getLocation(){
		return location;
	}

	public ObjectType getType() {
		return type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}