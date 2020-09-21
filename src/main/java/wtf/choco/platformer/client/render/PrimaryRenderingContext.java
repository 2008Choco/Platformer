package wtf.choco.platformer.client.render;

import java.util.IdentityHashMap;
import java.util.Map;

import wtf.choco.platformer.client.render.entity.BasicEntityRenderer;
import wtf.choco.platformer.client.render.tile.BasicTileRenderer;
import wtf.choco.platformer.engine.client.render.IPrimaryRenderer;
import wtf.choco.platformer.engine.client.render.IRenderContext;
import wtf.choco.platformer.engine.client.render.IRenderer;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.utils.TilePos;

public class PrimaryRenderingContext implements IRenderContext<PrimaryRenderingContext> {

    private final Map<Tile, IRenderer<TilePos>> tileRenderers = new IdentityHashMap<>();
    private final Map<Class<? extends Entity>, IRenderer<Entity>> entityRenderers = new IdentityHashMap<>();

    private final PrimaryGameRenderer renderer;

    PrimaryRenderingContext(PrimaryGameRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public IPrimaryRenderer<PrimaryRenderingContext> getRenderer() {
        return renderer;
    }

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
