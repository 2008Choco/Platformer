package wtf.choco.platformer.client.render.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.utils.Location;

public class BasicEntityRenderer implements IEntityRenderer {

    // TODO: This needs to be done more properly... a transformation matrix or something...
    private static final int PIXEL_RATIO = 32;

    @Override
    public void render(Graphics graphics, Entity entity) {
        Location location = entity.getLocation();
        Rectangle bounds = entity.getBounds();

        graphics.setColor(entity.getColour());
        graphics.fillRect((int) (location.getX() * PIXEL_RATIO), (int) (location.getY() * PIXEL_RATIO), bounds.width * PIXEL_RATIO, bounds.height * PIXEL_RATIO);
    }

}
