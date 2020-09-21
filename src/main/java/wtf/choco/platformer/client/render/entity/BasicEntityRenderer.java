package wtf.choco.platformer.client.render.entity;

import java.awt.Color;
import java.awt.Graphics;

import wtf.choco.platformer.client.render.IRenderer;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.utils.BoundingBox;
import wtf.choco.platformer.utils.Location;
import wtf.choco.platformer.utils.TilePos;

public class BasicEntityRenderer implements IRenderer<Entity> {

    @Override
    public void render(Graphics graphics, Entity entity) {
        Location location = entity.getLocation();
        BoundingBox bounds = entity.getBounds();

        graphics.setColor(entity.getColour());
        graphics.fillRect((int) (location.getX() * PIXEL_RATIO), (int) (location.getY() * PIXEL_RATIO), (int) (bounds.getWidth() * PIXEL_RATIO), (int) (bounds.getHeight() * PIXEL_RATIO));

        Location currentLocation = entity.getLocation();
        Location futureLocation = currentLocation.offset(entity.getVelocityX() / PIXEL_RATIO, entity.getVelocityY()  / PIXEL_RATIO);
        TilePos topLeft = futureLocation.toTilePos();
        TilePos topRight = new TilePos((int) (futureLocation.getX() + 1.0 - (1 / 32.0)), (int) futureLocation.getY());
        TilePos bottomLeft = new TilePos((int) futureLocation.getX(), (int) (futureLocation.getY() + 1.0 - (1 / 32.0)));
        TilePos bottomRight = new TilePos((int) (futureLocation.getX() + 1.0 - (1 / 32.0)), (int) (futureLocation.getY() + 1.0 - (1 / 32.0)));

        graphics.setColor(Color.ORANGE);
        graphics.drawRect(topLeft.getX() * PIXEL_RATIO, topLeft.getY() * PIXEL_RATIO, 32, 32);
        graphics.drawRect(topRight.getX() * PIXEL_RATIO, topRight.getY() * PIXEL_RATIO, 32, 32);
        graphics.drawRect(bottomLeft.getX() * PIXEL_RATIO, bottomLeft.getY() * PIXEL_RATIO, 32, 32);
        graphics.drawRect(bottomRight.getX() * PIXEL_RATIO, bottomRight.getY() * PIXEL_RATIO, 32, 32);
    }

}
