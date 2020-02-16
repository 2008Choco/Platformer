package wtf.choco.platformer.utils;

import java.util.Objects;

public class Location {

    private float x, y;

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Location setX(float x) {
        this.x = x;
        return this;
    }

    public float getX() {
        return x;
    }

    public Location setY(float y) {
        this.y = y;
        return this;
    }

    public float getY() {
        return y;
    }

    public double distance(Location location) {
        return Math.sqrt(distanceSquared(location));
    }

    public double distanceSquared(Location location) {
        return Math.pow(location.x - x, 2) + Math.pow(location.y - y, 2);
    }

    public Location up(float offset) {
        return new Location(x, y - offset);
    }

    public Location up() {
        return up(1);
    }

    public Location down(float offset) {
        return new Location(x, y + offset);
    }

    public Location down() {
        return down(1);
    }

    public Location left(float offset) {
        return new Location(x - offset, y);
    }

    public Location left() {
        return left(1);
    }

    public Location right(float offset) {
        return new Location(x + offset, y);
    }

    public Location right() {
        return right(1);
    }

    public Location offset(float x, float y) {
        return new Location(this.x + x, this.y + y);
    }

    public TilePos toTilePos() {
        return new TilePos((int) x, (int) y);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Location)) {
            return false;
        }

        Location other = (Location) object;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location:{x:" + x + ", y:" + y + "}";
    }

}
