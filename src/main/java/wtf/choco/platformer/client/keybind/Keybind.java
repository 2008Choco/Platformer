package wtf.choco.platformer.client.keybind;

import java.awt.event.InputEvent;

public class Keybind {

    private final int key;
    private final int modifiers;

    private Runnable pressCallback, releaseCallback;

    public Keybind(int key, int modifiers) {
        this.key = key;
        this.modifiers = modifiers;
    }

    public Keybind(int key) {
        this(key, 0);
    }

    public Keybind onPress(Runnable callback) {
        this.pressCallback = callback;
        return this;
    }

    public Keybind onRelease(Runnable callback) {
        this.releaseCallback = callback;
        return this;
    }

    public int getKey() {
        return key;
    }

    public int getModifiers() {
        return modifiers;
    }

    public boolean requiresShift() {
        return (modifiers & InputEvent.SHIFT_DOWN_MASK) != 0;
    }

    public boolean requiresCtrl() {
        return (modifiers & InputEvent.CTRL_DOWN_MASK) != 0;
    }

    public boolean requiresMeta() {
        return (modifiers & InputEvent.META_DOWN_MASK) != 0;
    }

    public boolean requiresAlt() {
        return (modifiers & InputEvent.ALT_DOWN_MASK) != 0;
    }

    public boolean matches(int key, int modifiers) {
        return this.key == key && this.modifiers == modifiers;
    }

    public void press() {
        if (pressCallback != null) {
            this.pressCallback.run();
        }
    }

    public void release() {
        if (releaseCallback != null) {
            this.releaseCallback.run();
        }
    }

}
