package wtf.choco.platformer.utils;

public class BoundingBox {

    private double x, y, width, height;

    public BoundingBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BoundingBox(double width, double height) {
        this(0, 0, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public BoundingBox move(double x, double y) {
        return new BoundingBox(this.x + x, this.y + y, width, height);
    }

    // Pulled from Rectangle#intersects()
    public boolean collides(BoundingBox other) {
        double tw = width;
        double th = height;
        double rw = other.width;
        double rh = other.height;

        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }

        double tx = x;
        double ty = y;
        double rx = other.x;
        double ry = other.y;

        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;

        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

}
