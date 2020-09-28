package wtf.choco.platformer.engine.client.keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of key codes and a list of actions to invoke if conditions
 * have been met to consider this keybind as pressed.
 */
public class Keybind {

    private final int[] keys, keysClone;
    private final List<KeybindListener> listeners;

    /**
     * Construct a new keybind with the given keys.
     *
     * @param keys the keys
     */
    public Keybind(int... keys) {
        if (keys.length == 0) {
            throw new IllegalArgumentException("Attempted to created keybind with no keys");
        }

        this.keys = keys;
        this.keysClone = new int[keys.length];
        this.listeners = new ArrayList<>();
    }

    /**
     * Get the keys that need to be pressed for this keybind to be valid.
     *
     * @return the keys
     */
    public int[] getKeys() {
        System.arraycopy(keys, 0, keysClone, 0, keys.length);
        return keysClone;
    }

    /**
     * Add a listener to this keybind to be called when it has been pressed.
     *
     * @param listener the listener to add
     *
     * @return this instance. Allows for chained method calls
     */
    public Keybind addListener(KeybindListener listener) {
        this.listeners.add(listener);
        return this;
    }

    /**
     * Add a list of listeners to this keybind to be called when it has been pressed.
     *
     * @param listeners the listeners to add
     */
    public void addListeners(KeybindListener... listeners) {
        for (KeybindListener listener : listeners) {
            this.addListener(listener);
        }
    }

    /**
     * Remove a listener from this keybind.
     *
     * @param listener the listener to remove
     */
    public void removeListener(KeybindListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Call all listeners on this keybind.
     */
    public void callListeners() {
        this.listeners.forEach(l -> l.onPress(this));
    }

}
