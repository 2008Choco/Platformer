package wtf.choco.platformer.engine.client.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard {

	private static final boolean[] KEYS = new boolean[1024];

	private Keyboard() { }

	public static boolean isPressed(int key) {
		return KEYS[key];
	}

	public static boolean arePressed(int... keys) {
		boolean allPressed = true;

		for (int key : keys) {
			if (isReleased(key)) {
			    allPressed = false;
			}
		}

		return allPressed;
	}

	public static boolean isReleased(int key) {
		return !KEYS[key];
	}

	public static boolean areReleased(int... keys) {
		boolean allReleased = true;

		for (int key : keys) {
			if (isPressed(key)) {
			    allReleased = false;
			}
		}

		return allReleased;
	}


	public static final class Listener implements KeyListener {

	    @Override
	    public void keyPressed(KeyEvent event) {
	        Keyboard.KEYS[event.getKeyCode()] = true;
	    }

	    @Override
	    public void keyReleased(KeyEvent event) {
	        Keyboard.KEYS[event.getKeyCode()] = false;
	    }

	    @Override
	    public void keyTyped(KeyEvent event) { } // Unused

	}

}