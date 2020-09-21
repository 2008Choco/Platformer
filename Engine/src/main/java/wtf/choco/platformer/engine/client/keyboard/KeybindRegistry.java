package wtf.choco.platformer.engine.client.keyboard;

import java.util.ArrayList;
import java.util.List;

public final class KeybindRegistry {

    private static final List<Keybind> KEYBINDS = new ArrayList<>();
    private static final List<KeybindListener> LISTENERS = new ArrayList<>();

    private KeybindRegistry() { }

    public static Keybind register(Keybind keybind) {
        if (keybind == null) {
            throw new IllegalArgumentException("Attempted to register null keybind");
        }

        KEYBINDS.add(keybind);
        return keybind;
    }

    public static void pollInput() {
        KEYBINDS.forEach(k -> {
            if (Keyboard.arePressed(k.getKeys())) {
                k.callListeners(); // Call key-specific listeners first
                LISTENERS.forEach(l -> l.onPress(k));
            }
        });
    }

    public static void addListener(KeybindListener listener) {
        LISTENERS.add(listener);
    }

    public static void addListener(KeybindListener listener, Keybind... keybinds) {
        for (Keybind keybind : keybinds) {
            keybind.addListener(listener);
        }
    }

}
