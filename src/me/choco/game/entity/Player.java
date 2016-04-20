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
		this.x += velX;
		this.y += velY;
		
		this.x = NumUtils.clamp(x, 0, Game.WIDTH - (int)(width*1.25));
		this.y = NumUtils.clamp(y, 0, Game.HEIGHT - (int)(height*2));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
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