package wtf.choco.platformer.engine.client;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.ImageCapabilities;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent.Cause;
import java.awt.image.BufferStrategy;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import wtf.choco.platformer.engine.client.render.IPrimaryRenderer;
import wtf.choco.platformer.engine.client.render.IRenderContext;

public class Window<C extends IRenderContext<C>, R extends IPrimaryRenderer<C>> {

    private String title;
    private int width, height;

    private int fps, localFPS;

    private R renderer;
    private C renderingContext;

    private WindowResizeCallback resizeCallback;

    private final JFrame frame;
    private final Canvas canvas;

    public Window(String title, int width, int height, R renderer) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.frame = new JFrame(title);

        this.frame.setSize(width, height);
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setLocationRelativeTo(null);
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

                if (resizeCallback != null) {
                    Window.this.resizeCallback.resize(Window.this.width, Window.this.height, width, height);
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

        // Initialize the drawing canvas
        this.canvas = new Canvas();
        this.frame.add(canvas);

        this.canvas.setFocusable(true);
        this.canvas.requestFocus(Cause.ACTIVATION);

        if (canvas.getBufferStrategy() == null) {
            try {
                this.canvas.createBufferStrategy(2, new BufferCapabilities(new ImageCapabilities(true), new ImageCapabilities(true), FlipContents.BACKGROUND));
            } catch (AWTException e) {
                System.err.println("Defaulting to buffer strategy with no capabilities");
                this.canvas.createBufferStrategy(2); // Default to unspecified capabilities
            }
        }

        this.canvas.setBackground(Color.BLACK);

        this.renderer = renderer;
        this.renderingContext = renderer.getContextSupplier().get();
    }

    public void init() {
        if (renderer != null) {
            this.renderer.init(renderingContext);
        }
    }

    public void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        this.renderer.render(graphics, renderingContext);
        this.localFPS++;

        bufferStrategy.show();
        graphics.dispose();
    }

    public void acceptListeners(Consumer<Component> consumer) {
        consumer.accept(canvas);
    }

    public void setResizeCallback(WindowResizeCallback resizeCallback) {
        this.resizeCallback = resizeCallback;
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

    public void saveFPS() {
        this.fps = localFPS;
        this.localFPS = 0;
    }

    public int getFPS() {
        return fps;
    }

}
