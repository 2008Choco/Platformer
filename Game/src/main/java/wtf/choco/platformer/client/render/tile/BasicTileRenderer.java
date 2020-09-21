package wtf.choco.platformer.client.render.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import wtf.choco.platformer.engine.client.render.IRenderer;
import wtf.choco.platformer.engine.client.texture.Texture;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.utils.TilePos;

public class BasicTileRenderer implements IRenderer<TilePos> {

    private final Tile tile;
    private final Texture texture;

    public BasicTileRenderer(Tile tile) {
        this.tile = tile;
        this.texture = Texture.fetch("/textures/tiles/" + tile.getId() + ".png");
    }

    @Override
    public void render(Graphics graphics, TilePos pos) {
        Rectangle bounds = tile.getBounds();
        graphics.drawImage(texture.asImage(), pos.getX() * PIXEL_RATIO, pos.getY() * PIXEL_RATIO, bounds.width * PIXEL_RATIO, bounds.height * PIXEL_RATIO, null);
    }

}
