package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import me.choco.game.world.Location;

public class Enemy extends Entity{
	
//	private double theta = 0;
	
	public Enemy(Location location, int width, int height) {
		super(location, width, height, ObjectType.ENEMY);
		velX = 3;
	}

	@Override
	public void tick() {
		// This is temporary code. Just for testing purposes (The enemy will go in circles)
//		location.setX(location.getX() + (int)(Math.sin(theta) * 5));
//		location.setY(location.getY() + (int)(Math.cos(theta) * 5));
//		theta += 0.05;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.getX() - camera.getXOffset(), location.getY() - camera.getYOffset(), width, height);
	}
}