package wtf.choco.platformer.client.keybind;

import java.util.ArrayList;
import java.util.List;

public class Keybind {

    private final int[] keys;
    private final List<KeybindListener> listeners;

    public Keybind(int... keys) {
        if (keys.length == 0) {
            throw new IllegalArgumentException("Attempted to created keybind with no keys");
        }

        this.keys = keys;
        this.listeners = new ArrayList<>();
    }

    public int[] getKeys() {
        return keys;
    }

    public Keybind addListener(KeybindListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public void removeListener(KeybindListener listener) {
        this.listeners.remove(listener);
    }

    public void callListeners() {
        this.listeners.forEach(l -> l.onPress(this));
    }

}
