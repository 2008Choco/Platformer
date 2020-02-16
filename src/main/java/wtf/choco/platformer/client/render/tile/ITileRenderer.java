package wtf.choco.platformer.client.render.tile;

import java.awt.Graphics;

import wtf.choco.platformer.utils.TilePos;

public interface ITileRenderer {

    public void render(Graphics graphics, TilePos pos);

}
