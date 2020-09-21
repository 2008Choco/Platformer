package wtf.choco.platformer.engine.client.render;

import java.util.IdentityHashMap;
import java.util.Map;

import wtf.choco.platformer.client.render.entity.BasicEntityRenderer;
import wtf.choco.platformer.client.render.tile.BasicTileRenderer;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.utils.TilePos;

/**
 * Represents a context to map objects to individual {@link IRenderer IRenderers}
 */
public class RenderingContext {

    // TODO: This all needs to be abstracted out of the engine

    private final Map<Tile, IRenderer<TilePos>> tileRenderers = new IdentityHashMap<>();
    private final Map<Class<? extends Entity>, IRenderer<Entity>> entityRenderers = new IdentityHashMap<>();

    public void bindRenderer(Tile tile, IRenderer<TilePos> renderer) {
        this.tileRenderers.put(tile, renderer);
    }

    public <T extends Entity> void bindRenderer(Class<T> entityClass, IRenderer<Entity> renderer) {
        this.entityRenderers.put(entityClass, renderer);
    }

    public IRenderer<TilePos> getRenderer(Tile tile) {
        return tileRenderers.computeIfAbsent(tile, BasicTileRenderer::new);
    }

    public IRenderer<Entity> getRenderer(Class<? extends Entity> entityClass) {
        return entityRenderers.computeIfAbsent(entityClass, c -> new BasicEntityRenderer());
    }

}
