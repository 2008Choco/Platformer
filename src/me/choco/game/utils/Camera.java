package me.choco.game.utils;

import java.util.HashSet;
import java.util.Set;

import me.choco.game.Game;
import me.choco.game.entity.GameObject;
import me.choco.game.entity.ObjectType;
import me.choco.game.world.Location;

public class Camera {
	
	private final Game game;
	private int xOffset = 0, yOffset = 0;
	public Camera(Game game, int xOffset, int yOffset){
		this.game = game;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public Camera(Game game){
		this(game, 0, 0);
	}
	
	public int getXOffset(){
		return xOffset;
	}
	
	public void setXOffset(int xOffset){
		this.xOffset = xOffset;
	}
	
	public int getYOffset(){
		return yOffset;
	}
	
	public void setYOffset(int yOffset){
		this.yOffset = yOffset;
	}
	
	public Set<GameObject> getVisibleObjects(ObjectType type){
		Set<GameObject> entities = new HashSet<>();
		for (GameObject object : game.getEntityHandler().getObjects()){
			if (!object.getType().equals(type)) continue;
			
			Location loc = object.getLocation();
			if (!(loc.getX() >= 0 + xOffset && loc.getX() <= Game.WIDTH + xOffset
					&& loc.getY() >= 0 + yOffset && loc.getY() <= Game.HEIGHT + yOffset))
				continue;
			
			entities.add(object);
		}
		return entities;
	}
}