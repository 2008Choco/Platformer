package wtf.choco.platformer.utils;

public class TilePos {

    private final int x, y;

    public TilePos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TilePos up(int offset) {
        return new TilePos(x, y - offset);
    }

    public TilePos up() {
        return up(1);
    }

    public TilePos down(int offset) {
        return new TilePos(x, y + offset);
    }

    public TilePos down() {
        return down(1);
    }

    public TilePos left(int offset) {
        return new TilePos(x - offset, y);
    }

    public TilePos left() {
        return left(1);
    }

    public TilePos right(int offset) {
        return new TilePos(x + offset, y);
    }

    public TilePos right() {
        return right(1);
    }

    public TilePos offset(int x, int y) {
        return new TilePos(this.x + x, this.y + y);
    }

    public Location toLocation() {
        return new Location(x, y);
    }

    @Override
    public String toString() {
        return "BlockPos:[x:" + x + ", y:" + y + "]";
    }

}
