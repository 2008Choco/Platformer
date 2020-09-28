package wtf.choco.platformer.engine.client;

/**
 * A callback invoked when a window is resized.
 */
@FunctionalInterface
public interface WindowResizeCallback {

    /**
     * Called when the window is resized.
     *
     * @param width the window's current width
     * @param height the window's current height
     * @param widthNew the new window width
     * @param heightNew the new window height
     */
    public void resize(int width, int height, int widthNew, int heightNew);

}
