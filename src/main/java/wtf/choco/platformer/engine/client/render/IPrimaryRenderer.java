package wtf.choco.platformer.engine.client.render;

import java.awt.Graphics;
import java.util.function.Supplier;

import wtf.choco.platformer.client.Window;

/**
 * A primary renderer for a {@link Window}.
 */
public interface IPrimaryRenderer {

    /**
     * Initialize the rendering context for this renderer.
     *
     * @param context the context to initialize
     */
    public void init(RenderingContext context);

    /**
     * Invoke a render call on the given graphics object.
     *
     * @param graphics the graphics with which to draw
     * @param context this renderer's context
     */
    public void render(Graphics graphics, RenderingContext context);

    /**
     * Get a supplier to generate a rendering context for this renderer.
     *
     * @return the rendering context supplier
     */
    public default Supplier<RenderingContext> getContextSupplier() {
        return RenderingContext::new;
    }

}
