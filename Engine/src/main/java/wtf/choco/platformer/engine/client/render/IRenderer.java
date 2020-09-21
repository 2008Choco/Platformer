package wtf.choco.platformer.engine.client.render;

import java.awt.Graphics;

/**
 * A renderer for a specific game object.
 *
 * @param <T> the object type to render
 */
public interface IRenderer<T> {

    // TODO: This needs to be done more properly... a transformation matrix or something...
    static final int PIXEL_RATIO = 32;

    /**
     * Render the provided object to the given graphics context.
     *
     * @param graphics the graphics with which to draw
     * @param object the object to draw
     */
    public void render(Graphics graphics, T object);

}
