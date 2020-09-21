package wtf.choco.platformer.client;

import java.awt.event.KeyEvent;

import wtf.choco.platformer.client.listener.KeybindListenerGameplay;
import wtf.choco.platformer.client.listener.KeybindListenerMisc;
import wtf.choco.platformer.engine.client.keyboard.Keybind;
import wtf.choco.platformer.engine.client.keyboard.KeybindRegistry;

public final class Keybinds {

    public static final Keybind KEYBIND_SHOW_DEBUG_INFO = KeybindRegistry.register(new Keybind(KeyEvent.VK_D, KeyEvent.VK_CONTROL));
    public static final Keybind KEYBIND_SHOW_HITBOXES = KeybindRegistry.register(new Keybind(KeyEvent.VK_B, KeyEvent.VK_CONTROL));
    public static final Keybind KEYBIND_ESCAPE_MENU = KeybindRegistry.register(new Keybind(KeyEvent.VK_ESCAPE));

    public static final Keybind KEYBIND_JUMP = KeybindRegistry.register(new Keybind(KeyEvent.VK_W));
    public static final Keybind KEYBIND_DOWN = KeybindRegistry.register(new Keybind(KeyEvent.VK_S));
    public static final Keybind KEYBIND_LEFT = KeybindRegistry.register(new Keybind(KeyEvent.VK_A));
    public static final Keybind KEYBIND_RIGHT = KeybindRegistry.register(new Keybind(KeyEvent.VK_D));

    private Keybinds() { }

    public static void init() {
        KeybindRegistry.addListener(KeybindListenerMisc.INSTANCE);
        KeybindRegistry.addListener(KeybindListenerGameplay.INSTANCE, KEYBIND_JUMP, KEYBIND_DOWN, KEYBIND_LEFT, KEYBIND_RIGHT);
    }

}
