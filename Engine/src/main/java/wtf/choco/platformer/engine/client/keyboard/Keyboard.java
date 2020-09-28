package wtf.choco.platformer.engine.client.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A utility class to poll data from the keyboard.
 *
 * @see KeyEvent KeyEvent for key constants
 */
public final class Keyboard {

	private static final boolean[] KEYS = new boolean[1024];

	private Keyboard() { }

	/**
	 * Check whether or not the given key has been pressed.
	 *
	 * @param key the key to check
	 *
	 * @return true if pressed, false otherwise
	 */
	public static boolean isPressed(int key) {
		return KEYS[key];
	}

	/**
	 * Check whether or not all of the given keys have been pressed.
	 *
	 * @param keys the keys to check
	 *
	 * @return true if all pressed, false if one or more are released
	 */
	public static boolean arePressed(int... keys) {
		for (int key : keys) {
			if (isReleased(key)) {
			    return false;
			}
		}

		return true;
	}

    /**
     * Check whether or not the given key has been released.
     *
     * @param key the key to check
     *
     * @return true if released, false otherwise
     */
	public static boolean isReleased(int key) {
		return !KEYS[key];
	}

    /**
     * Check whether or not all of the given keys have been released.
     *
     * @param keys the keys to check
     *
     * @return true if all released, false if one or more are pressed
     */
	public static boolean areReleased(int... keys) {
		for (int key : keys) {
			if (isPressed(key)) {
			    return false;
			}
		}

		return true;
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