package wtf.choco.platformer.engine.client.keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a registry in which keybinds may be polled.
 */
public final class KeybindRegistry {

    private static final List<Keybind> KEYBINDS = new ArrayList<>();
    private static final List<KeybindListener> LISTENERS = new ArrayList<>();

    private KeybindRegistry() { }

    /**
     * Register a keybind.
     *
     * @param keybind the keybind to register
     *
     * @return the registered keybind
     */
    public static Keybind register(Keybind keybind) {
        if (keybind == null) {
            throw new IllegalArgumentException("Attempted to register null keybind");
        }

        KEYBINDS.add(keybind);
        return keybind;
    }

    /**
     * Poll for input and call any keybind listeners where conditions are met.
     */
    public static void pollInput() {
        KEYBINDS.forEach(k -> {
            if (Keyboard.arePressed(k.getKeys())) {
                k.callListeners(); // Call key-specific listeners first
                LISTENERS.forEach(l -> l.onPress(k));
            }
        });
    }

    /**
     * Add a general keybind listener.
     *
     * @param listener the listener to add
     */
    public static void addListener(KeybindListener listener) {
        LISTENERS.add(listener);
    }

    /**
     * Add a keybind listener to each keybind.
     *
     * @param listener the listener to add
     * @param keybinds the keybinds to which the listener should be added
     */
    public static void addListener(KeybindListener listener, Keybind... keybinds) {
        for (Keybind keybind : keybinds) {
            keybind.addListener(listener);
        }
    }

}
