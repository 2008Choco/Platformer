package wtf.choco.platformer.entity;

import java.awt.Color;
import java.awt.Rectangle;

import wtf.choco.platformer.utils.Location;

public abstract class Entity {

    protected Location location;

    protected int width, height;
    protected float velX = 0, velY = 0;
    protected boolean airborn = true;
    protected Color colour = new Color(0xFFFFFF);

    private final Rectangle bounds;

    protected Entity(Location location, int width, int height) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(width, height);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isAirborn() {
        return this.airborn;
    }

    public void setAirborn(boolean airborn) {
        this.airborn = airborn;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getGravity() {
        return 0.25F;
    }

    public void tick() {
        this.location = location.offset(velX, velY);
    }

}
