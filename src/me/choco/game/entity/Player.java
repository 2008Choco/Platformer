package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import me.choco.game.Game;
import me.choco.game.entity.variants.Controllable;
import me.choco.game.utils.general.NumUtils;
import me.choco.game.utils.listeners.KeyboardListener;

public class Player extends Entity implements Controllable{
	public Player(int x, int y){
		super(x, y, 30, 30, ObjectType.PLAYER);
	}

	@Override
	public void tick(){
		setX(getX() + velX);
		setY(getY() + velY);
		
		setX(NumUtils.clamp(getX(), 0, Game.WIDTH - 47)); // Magic numbers? Not sure why
		setY(NumUtils.clamp(getY(), 0, Game.HEIGHT - 69)); 
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.getX(), location.getY(), width, height);
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