package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import me.choco.game.Game;
import me.choco.game.entity.variants.Controllable;
import me.choco.game.utils.listeners.KeyboardListener;
import me.choco.game.world.Location;

public class Player extends Entity implements Controllable{
	
	public Player(Location location){
		super(location, 30, 30, ObjectType.PLAYER);
	}

	@Override
	public void tick(){
		location.setX(location.getX() + velX);
		location.setY(location.getY() + velY);
		
		// Bounding limitations
		if ((location.getX() + 47) - camera.getXOffset() > Game.WIDTH) camera.setXOffset(camera.getXOffset() + 3);
		else if (location.getX() - camera.getXOffset() < 0) camera.setXOffset(camera.getXOffset() - 3);
		
		if ((location.getY() + 69) - camera.getYOffset() > Game.HEIGHT) camera.setYOffset(camera.getYOffset() + 3);
		else if (location.getY() - camera.getYOffset() < 0) camera.setYOffset(camera.getYOffset() - 3);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.getX() - camera.getXOffset(), location.getY() - camera.getYOffset(), width, height);
		//TODO Render sprite
	}

	@Override
	public void onPressKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyPressed(KeyEvent.VK_W)){ this.setVelY(-3); }
		if (listener.isKeyPressed(KeyEvent.VK_A)){ this.setVelX(-3); }
		if (listener.isKeyPressed(KeyEvent.VK_S)){ this.setVelY(3); }
		if (listener.isKeyPressed(KeyEvent.VK_D)){ this.setVelX(3); }
	}

	@Override
	public void onReleaseKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyReleased(KeyEvent.VK_W)){ this.setVelY(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_A)){ this.setVelX(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_S)){ this.setVelY(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_D)){ this.setVelX(0); onPressKey(event, listener); }
	}
}