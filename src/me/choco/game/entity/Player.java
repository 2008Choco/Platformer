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
		location.setX(getX() + velX);
		location.setY(getY() + velY);
		
		// Bounding limitations
		if (location.getScreenX() > Game.WIDTH - 47) location.setScreenX(Game.WIDTH - 47);
		else if (location.getScreenX() < 0) location.setScreenX(0);
		if (location.getScreenY() > Game.HEIGHT - 69) location.setScreenY(Game.HEIGHT - 69);
		else if (location.getScreenY() < 0) location.setScreenY(0);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.getScreenX(), location.getScreenY(), width, height);
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