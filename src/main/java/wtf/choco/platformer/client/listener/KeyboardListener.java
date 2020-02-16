package wtf.choco.platformer.client.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.keybind.Keybind;
import wtf.choco.platformer.client.keybind.KeybindRegistry;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.entity.variants.Controllable;

public class KeyboardListener implements KeyListener {

	private final boolean[] keys = new boolean[1024];

	private final Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		this.keys[event.getKeyCode()] = true;
		KeybindRegistry.forAllMatchingKeybinds(event.getKeyCode(), event.getModifiersEx(), Keybind::press);

		// TODO: Move this somewhere else
		if (game.getActiveMenu() == null) {
			for (Entity entity : game.getEntityHandler().getEntities()) {
				if (entity instanceof Controllable) {
				    ((Controllable) entity).onPressKey(event, this);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		this.keys[event.getKeyCode()] = false;
		KeybindRegistry.forAllMatchingKeybinds(event.getKeyCode(), event.getModifiersEx(), Keybind::release);

		// TODO: Move this somewhere else
		if (game.getActiveMenu() == null) {
		    for (Entity entity : game.getEntityHandler().getEntities()) {
	            if (entity instanceof Controllable) {
	                ((Controllable) entity).onReleaseKey(event, this);
	            }
	        }
		}
	}

	@Override
    public void keyTyped(KeyEvent event) { } // Unused

	public boolean isKeyPressed(int key) {
		return keys[key];
	}

	public boolean areKeysPressed(int... keys) {
		boolean allPressed = true;

		for (int key : keys) {
			if (isKeyReleased(key)) {
			    allPressed = false;
			}
		}

		return allPressed;
	}

	public boolean isKeyReleased(int key) {
		return !keys[key];
	}

	public boolean areKeysReleased(int... keys) {
		boolean allReleased = true;

		for (int key : keys) {
			if (isKeyPressed(key)) {
			    allReleased = false;
			}
		}

		return allReleased;
	}

}