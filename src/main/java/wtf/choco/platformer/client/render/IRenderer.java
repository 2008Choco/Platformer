package wtf.choco.platformer.client.render;

import java.awt.Graphics;

public interface IRenderer<T> {

    public void render(Graphics graphics, T object);

}
