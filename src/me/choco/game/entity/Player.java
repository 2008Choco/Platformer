package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import me.choco.game.Game;
import me.choco.game.entity.variants.Controllable;
import me.choco.game.entity.variants.Gravity;
import me.choco.game.utils.listeners.KeyboardListener;
import me.choco.game.world.Location;

public class Player extends Entity implements Controllable, Gravity{
	
	private int speed = 3;
	
	public Player(Location location){
		super(location, 32, 32, ObjectType.PLAYER);
	}

	@Override
	public void tick(){
		location.setRawX(location.getRawX() + (int) velX);
		location.setRawY(location.getRawY() + (int) velY);
		
		// Bounding limitations
		if ((location.getRawX() + 47) - camera.getXOffset() > Game.WIDTH) camera.setXOffset(camera.getXOffset() + speed);
		else if (location.getRawX() - camera.getXOffset() < 0) camera.setXOffset(camera.getXOffset() - speed);
		
		if ((location.getRawY() + 69) - camera.getYOffset() > Game.HEIGHT) camera.setYOffset(camera.getYOffset() + speed);
		else if (location.getRawY() - camera.getYOffset() < 0) camera.setYOffset(camera.getYOffset() - speed);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.getRawX() - camera.getXOffset(), location.getRawY() - camera.getYOffset(), width, height);
		//TODO Render sprite
	}

	@Override
	public void onPressKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyPressed(KeyEvent.VK_W)){ this.setVelY(-speed); }
		if (listener.isKeyPressed(KeyEvent.VK_A)){ this.setVelX(-speed); }
		if (listener.isKeyPressed(KeyEvent.VK_S)){ this.setVelY(speed); }
		if (listener.isKeyPressed(KeyEvent.VK_D)){ this.setVelX(speed); }
	}

	@Override
	public void onReleaseKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyReleased(KeyEvent.VK_W)){ this.setVelY(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_A)){ this.setVelX(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_S)){ this.setVelY(0); onPressKey(event, listener); }
		if (listener.isKeyReleased(KeyEvent.VK_D)){ this.setVelX(0); onPressKey(event, listener); }
	}

	@Override
	public float getGravityValue() {
		return 1;
	}
}