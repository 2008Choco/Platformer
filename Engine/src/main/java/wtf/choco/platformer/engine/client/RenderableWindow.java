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

/**
 * Represents a window to which a renderer may draw content.
 *
 * @param <C> the render context implementation
 * @param <R> the primary renderer implementation
 */
public class RenderableWindow<C extends IRenderContext<C>, R extends IPrimaryRenderer<C>> {

    private String title;
    private int width, height;

    private R renderer;
    private C renderingContext;

    private WindowResizeCallback resizeCallback;

    private final JFrame frame;
    private final Canvas canvas;

    /**
     * Construct a new window with a given title, width, height and primary renderer.
     *
     * @param title the window title
     * @param width the window width in pixels
     * @param height the window height in pixels
     * @param renderer the primary renderer
     */
    public RenderableWindow(String title, int width, int height, R renderer) {
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
                if (width == RenderableWindow.this.width && height == RenderableWindow.this.height) {
                    return;
                }

                if (resizeCallback != null) {
                    RenderableWindow.this.resizeCallback.resize(RenderableWindow.this.width, RenderableWindow.this.height, width, height);
                }

                // Update width and height
                RenderableWindow.this.width = width;
                RenderableWindow.this.height = height;
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

    /**
     * Initialize this window.
     */
    public void init() {
        if (renderer != null) {
            this.renderer.init(renderingContext);
        }
    }

    /**
     * Render this window.
     */
    public void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        this.renderer.render(graphics, renderingContext);

        bufferStrategy.show();
        graphics.dispose();
    }

    /**
     * Accept listeners for this window.
     *
     * @param consumer the consumer
     *
     * @deprecated Soon to be hidden behind the engine. Should not be called by implementations.
     */
    @Deprecated
    public void acceptListeners(Consumer<Component> consumer) {
        consumer.accept(canvas);
    }

    /**
     * Set the callback to be invoked when the window is resized.
     *
     * @param resizeCallback the resize callback
     */
    public void setResizeCallback(WindowResizeCallback resizeCallback) {
        this.resizeCallback = resizeCallback;
    }

    /**
     * Get this window's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get this window's width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get this window's height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

}
