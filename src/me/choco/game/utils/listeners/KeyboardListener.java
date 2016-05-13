package me.choco.game.utils.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import me.choco.game.Game;
import me.choco.game.Game.GameState;
import me.choco.game.entity.GameObject;
import me.choco.game.entity.variants.Controllable;
import me.choco.game.utils.EntityHandler;

public class KeyboardListener implements KeyListener{
	
	private final boolean[] keys = new boolean[1024];
	
	private Game game;
	private EntityHandler handler;
	public KeyboardListener(Game game){
		this.game = game;
		this.handler = game.getEntityHandler();
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		keys[event.getKeyCode()] = true;
		
		if (areKeysPressed(KeyEvent.VK_CONTROL, KeyEvent.VK_D)){
			game.setDebugMode(!game.isInDebugMode());
			return;
		}
		
		if (isKeyPressed(KeyEvent.VK_ESCAPE)){
			if (game.getState().equals(Game.GameState.GAME)) game.setState(Game.GameState.MAIN_MENU);
			else if (game.getState().equals(Game.GameState.MAIN_MENU)) game.setState(Game.GameState.GAME);
			else game.setState(GameState.MAIN_MENU);
			return;
		}
		
		for (GameObject object : handler.getEntities()){
			if (!game.getState().equals(GameState.GAME)) return;
			if (object instanceof Controllable)
				((Controllable) object).onPressKey(event, this);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent event) {
		keys[event.getKeyCode()] = false;
		
		for (GameObject object : handler.getEntities()){
			if (!game.getState().equals(GameState.GAME)) return;
			if (object instanceof Controllable)
				((Controllable) object).onReleaseKey(event, this);
		}
	}
	
	public void keyTyped(KeyEvent e){ /* Null method. Here to stop KeyListener from yelling at me */ }
	
	public boolean isKeyPressed(int key){
		return keys[key];
	}
	
	public boolean areKeysPressed(int... keys){
		boolean allPressed = true;
		for (int key : keys)
			if (isKeyReleased(key)) allPressed = false;
		return allPressed;
	}
	
	public boolean isKeyReleased(int key){
		return !keys[key];
	}
	
	public boolean areKeysReleased(int... keys){
		boolean allReleased = true;
		for (int key : keys)
			if (isKeyPressed(key)) allReleased = false;
		return allReleased;
	}
}