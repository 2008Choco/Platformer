package wtf.choco.platformer.entity;

import java.awt.Color;
import java.awt.Rectangle;

import wtf.choco.platformer.utils.Location;

public abstract class Entity {

    private Location location;

    private int width, height;
    private float velocityX = 0, velocityY = 0;
    private boolean airborn = true;
    private Color colour = new Color(0xFFFFFF);

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

    public void setVelocity(float x, float y) {
        this.velocityX = x;
        this.velocityY = y;
    }

    public void setVelocityX(float x) {
        this.velocityX = x;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityY(float y) {
        this.velocityY = y;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setAirborn(boolean airborn) {
        this.airborn = airborn;
    }

    public boolean isAirborn() {
        return this.airborn;
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

    public void tick() { }

}
