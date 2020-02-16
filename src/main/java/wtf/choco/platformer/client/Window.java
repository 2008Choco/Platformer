package wtf.choco.platformer.client;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.render.GameRenderBase;
import wtf.choco.platformer.client.render.texture.Texture;
import wtf.choco.platformer.menu.GameMenu;

public class Window {

    private String title;
    private int width, height;

    private final JFrame frame;

    public Window(Game game, String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.frame = new JFrame(title + " - " + Game.VERSION);

        this.frame.setSize(width, height);
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setLocationRelativeTo(null);
        this.frame.setIconImage(Texture.GENERIC_ICON.asImage());
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);

        this.frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent event) {
                Component component = event.getComponent();
                int width = component.getWidth(), height = component.getHeight();
                if (width == Window.this.width && height == Window.this.height) {
                    return;
                }

                GameMenu activeMenu = game.getActiveMenu();
                if (activeMenu != null) {
                    activeMenu.onUpdateWindow(Window.this, Window.this.width, Window.this.height, width, height);
                }

                // Update width and height
                Window.this.width = width;
                Window.this.height = height;
            }

            @Override
            public void componentHidden(ComponentEvent event) { }
            @Override
            public void componentMoved(ComponentEvent event) { }
            @Override
            public void componentShown(ComponentEvent event) { }
        });
    }

    public void bindRenderer(GameRenderBase renderer) {
        this.frame.add(renderer);
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
