package wtf.choco.platformer.engine.client.keyboard;

/**
 * Represents a listener called when a {@link Keybind} was pressed.
 */
@FunctionalInterface
public interface KeybindListener {

    /**
     * Called when the keybind is pressed.
     *
     * @param keybind the keybind
     */
    public void onPress(Keybind keybind);

}
