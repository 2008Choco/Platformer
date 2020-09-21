package wtf.choco.platformer.client.keybind;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import wtf.choco.platformer.client.listener.KeybindListenerGameplay;
import wtf.choco.platformer.client.listener.KeybindListenerMisc;

public class KeybindRegistry {

    private static final List<Keybind> KEYBINDS = new ArrayList<>();
    private static final List<KeybindListener> LISTENERS = new ArrayList<>();

    public static final Keybind KEYBIND_SHOW_DEBUG_INFO = register(new Keybind(KeyEvent.VK_D, KeyEvent.VK_CONTROL));
    public static final Keybind KEYBIND_SHOW_HITBOXES = register(new Keybind(KeyEvent.VK_B, KeyEvent.VK_CONTROL));
    public static final Keybind KEYBIND_ESCAPE_MENU = register(new Keybind(KeyEvent.VK_ESCAPE));

    public static final Keybind KEYBIND_JUMP = register(new Keybind(KeyEvent.VK_W));
    public static final Keybind KEYBIND_DOWN = register(new Keybind(KeyEvent.VK_S));
    public static final Keybind KEYBIND_LEFT = register(new Keybind(KeyEvent.VK_A));
    public static final Keybind KEYBIND_RIGHT = register(new Keybind(KeyEvent.VK_D));


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

    public static void init() {
        KeybindRegistry.addListener(KeybindListenerMisc.INSTANCE);
        KeybindRegistry.addListener(KeybindListenerGameplay.INSTANCE, KEYBIND_JUMP, KEYBIND_DOWN, KEYBIND_LEFT, KEYBIND_RIGHT);
    }

}
