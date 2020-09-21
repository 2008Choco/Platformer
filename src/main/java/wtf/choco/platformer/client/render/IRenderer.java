package wtf.choco.platformer.client.render;

import java.awt.Graphics;

public interface IRenderer<T> {

    // TODO: This needs to be done more properly... a transformation matrix or something...
    static final int PIXEL_RATIO = 32;

    public void render(Graphics graphics, T object);

}
