package wtf.choco.platformer.engine.client.render;

/**
 * Represents a context bound to a renderer.
 *
 * @param <T> itself
 */
public interface IRenderContext<T extends IRenderContext<T>> {

    /**
     * Get the renderer to which this context is bound.
     *
     * @return the context's renderer
     */
    public IPrimaryRenderer<T> getRenderer();

}
