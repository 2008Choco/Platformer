package me.choco.game.entity.variants;

import java.awt.event.KeyEvent;

import me.choco.game.utils.listeners.KeyboardListener;

public interface Controllable {
	public void onPressKey(KeyEvent event, KeyboardListener listener);
	
	public void onReleaseKey(KeyEvent event, KeyboardListener listener);
}