package me.choco.game.utils;

import java.util.HashSet;
import java.util.Set;

import me.choco.game.Game;
import me.choco.game.entity.Entity;
import me.choco.game.entity.GameObject;
import me.choco.game.entity.ObjectType;
import me.choco.game.world.Location;

public class Camera implements Runnable{
	
	private Thread thread;
	private Entity center;
	
	private boolean lockLocation = true;
	
	private final Game game;
	private int xOffset = 0, yOffset = 0;
	public Camera(Game game, int xOffset, int yOffset){
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		thread = new Thread(this);
	}
	
	public Camera(Game game){
		this(game, 0, 0);
	}
	
	@Override
	public void run(){
		while (game.isRunning()){
			if (!lockLocation) continue;
			
			while (center == null){
				EntityHandler handler = game.getEntityHandler();
				if (handler == null) continue;
				
				center = (Entity) game.getEntityHandler().getFirst(ObjectType.PLAYER);
			}
			
			synchronized (game.getMainThread()){
				xOffset = center.getLocation().getRawX() - (Game.WIDTH / 2) + (center.getWidth() / 2);
				// yOffset = center.getLocation().getRawY() - (Game.HEIGHT / 2) + (center.getHeight() / 2);
			}
		}
		
		try { thread.join(); } 
		catch (InterruptedException e) { e.printStackTrace(); }
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
			if (!(loc.getRawX() >= 0 + xOffset && loc.getRawX() <= Game.WIDTH + xOffset
					&& loc.getRawY() >= 0 + yOffset && loc.getRawY() <= Game.HEIGHT + yOffset))
				continue;
			
			entities.add(object);
		}
		return entities;
	}
	
	public void start(){
		if (thread.isAlive()) return;
		thread.start();
	}
}