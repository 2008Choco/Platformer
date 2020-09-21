package wtf.choco.platformer.engine.client.render;

import java.awt.Graphics;
import java.util.function.Supplier;

/**
 * A primary renderer for a window.
 *
 * @param <T> the type of render context
 */
public interface IPrimaryRenderer<T extends IRenderContext<T>> {

    /**
     * Initialize the rendering context for this renderer.
     *
     * @param context the context to initialize
     */
    public void init(T context);

    /**
     * Invoke a render call on the given graphics object.
     *
     * @param graphics the graphics with which to draw
     * @param context this renderer's context
     */
    public void render(Graphics graphics, T context);

    /**
     * Get a supplier to generate a rendering context for this renderer.
     *
     * @return the rendering context supplier
     */
    public Supplier<T> getContextSupplier();

}
