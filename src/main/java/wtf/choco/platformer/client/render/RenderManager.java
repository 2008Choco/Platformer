package wtf.choco.platformer.client.render;

import java.awt.Font;
import java.util.IdentityHashMap;
import java.util.Map;

import wtf.choco.platformer.client.render.entity.BasicEntityRenderer;
import wtf.choco.platformer.client.render.tile.BasicTileRenderer;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.utils.TilePos;

public class RenderManager {

    public static final Font FONT_ARIAL_BOLD_16 = new Font("Arial", Font.BOLD, 16);
    public static final Font FONT_COMICSANSMS_BOLD_29 = new Font("Comic Sans MS", Font.BOLD, 29);

    private static final Map<Tile, IRenderer<TilePos>> TILE_RENDERERS = new IdentityHashMap<>();
    private static final Map<Class<? extends Entity>, IRenderer<Entity>> ENTITY_RENDERERS = new IdentityHashMap<>();

    protected RenderManager() { }

    public static void bindRenderer(Tile tile, BasicTileRenderer renderer) {
        TILE_RENDERERS.put(tile, renderer);
    }

    public static <T extends Entity> void bindRenderer(Class<T> entityClass, IRenderer<Entity> renderer) {
        ENTITY_RENDERERS.put(entityClass, renderer);
    }

    public static IRenderer<TilePos> getRenderer(Tile tile) {
        return TILE_RENDERERS.computeIfAbsent(tile, BasicTileRenderer::new);
    }

    public static IRenderer<Entity> getRenderer(Class<? extends Entity> entityClass) {
        return ENTITY_RENDERERS.computeIfAbsent(entityClass, c -> new BasicEntityRenderer());
    }

}
