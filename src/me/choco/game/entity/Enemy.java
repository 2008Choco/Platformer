package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import me.choco.game.entity.variants.Gravitational;
import me.choco.game.world.Location;

public class Enemy extends Entity implements Gravitational{
	
	public Enemy(Location location) {
		super(location, Location.PIXEL_RATIO, Location.PIXEL_RATIO, ObjectType.ENEMY);
	}

	@Override
	public void tick() {
		location.setRawX(location.getRawX() + (int) velX);
		location.setRawY(location.getRawY() + (int) velY);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.getRawX() - camera.getXOffset(), location.getRawY() - camera.getYOffset(), width, height);
	}

	@Override
	public float getGravityValue() {
		return 0.25f;
	}
}