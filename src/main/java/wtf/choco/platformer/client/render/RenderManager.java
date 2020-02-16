package wtf.choco.platformer.client.render;

import java.util.IdentityHashMap;
import java.util.Map;

import wtf.choco.platformer.client.render.entity.BasicEntityRenderer;
import wtf.choco.platformer.client.render.entity.IEntityRenderer;
import wtf.choco.platformer.client.render.tile.BasicTileRenderer;
import wtf.choco.platformer.client.render.tile.ITileRenderer;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.tile.Tile;

public class RenderManager {

    private static final Map<Tile, ITileRenderer> TILE_RENDERERS = new IdentityHashMap<>();
    private static final Map<Class<? extends Entity>, IEntityRenderer> ENTITY_RENDERERS = new IdentityHashMap<>();

    protected RenderManager() { }

    public static void bindRenderer(Tile tile, BasicTileRenderer renderer) {
        TILE_RENDERERS.put(tile, renderer);
    }

    public static <T extends Entity> void bindRenderer(Class<T> entityClass, IEntityRenderer renderer) {
        ENTITY_RENDERERS.put(entityClass, renderer);
    }

    public static ITileRenderer getRenderer(Tile tile) {
        return TILE_RENDERERS.computeIfAbsent(tile, BasicTileRenderer::new);
    }

    public static IEntityRenderer getRenderer(Class<? extends Entity> entityClass) {
        return ENTITY_RENDERERS.computeIfAbsent(entityClass, c -> new BasicEntityRenderer());
    }

}
