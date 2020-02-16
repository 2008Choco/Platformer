package wtf.choco.platformer.client.render;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.ImageCapabilities;
import java.awt.event.FocusEvent.Cause;
import java.awt.image.BufferStrategy;
import java.text.DecimalFormat;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.utils.Location;
import wtf.choco.platformer.utils.NumberUtils;

public class GameRenderBase extends Canvas {

    // This class is never serialized so this UID can be ignored
    private static final long serialVersionUID = -1L;
    private static final DecimalFormat COORDINATE_FORMAT = new DecimalFormat("0.00");

    private int fps, localFPS;

    private final Game game;
    private final Window window;
    private final RenderManager renderManager;

    public GameRenderBase(Game game, Window window) {
        this.game = game;
        this.window = window;
        this.renderManager = new RenderManager();
    }

    public void init() {
        this.setFocusable(true);
        this.requestFocus(Cause.ACTIVATION);

        if (getBufferStrategy() == null) {
            try {
                this.createBufferStrategy(2, new BufferCapabilities(new ImageCapabilities(true), new ImageCapabilities(true), FlipContents.BACKGROUND));
            } catch (AWTException e) {
                System.err.println("Defaulting to buffer strategy with no capabilities");
                this.createBufferStrategy(2); // Default to unspecified capabilities
            }
        }

        this.setBackground(Color.BLACK);
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        if (game.level != null) {
            this.game.level.render(graphics);
        }

        if (game.activeMenu != null) {
            this.game.activeMenu.render(graphics);
        }

        if (Game.Debug.debugInformation) {
            graphics.setFont(GameFont.ARIAL_BOLD_16.getFont());
            graphics.setColor(fps >= 60 ? Color.GREEN : (fps >= 30 ? Color.YELLOW : Color.RED));
            graphics.drawString("FPS: " + fps, 5, 16);

            graphics.setColor(Color.YELLOW);
            graphics.drawString("TPS: " + game.getTPS(), 5, 32);

            // Player debug information
            if (game.player != null) {
                graphics.setFont(GameFont.ARIAL_BOLD_16.getFont());
                graphics.setColor(Color.YELLOW);

                Location location = game.player.getLocation();
                String x = "World X: " + COORDINATE_FORMAT.format(location.getX());
                String y = "World Y: " + COORDINATE_FORMAT.format(location.getY());

                int width = game.getWindow().getWidth();
                FontMetrics fontMetrics = graphics.getFontMetrics();
                graphics.drawString(x, width - (fontMetrics.stringWidth(x) + 20), 15);
                graphics.drawString(y, width - (fontMetrics.stringWidth(y) + 20), 30);
            }
        }

        graphics.setFont(graphics.getFont().deriveFont(Font.ITALIC, 10F));
        graphics.setColor(Color.GRAY);
        graphics.drawString(Game.VERSION, (window.getWidth() / 2) - NumberUtils.center(graphics, Game.VERSION), 10);

        bufferStrategy.show();
        graphics.dispose();

        this.localFPS++;
    }

    public void saveFrameCount() {
        this.fps = localFPS;
        this.localFPS = 0;
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }

    public int getFPS() {
        return fps;
    }

}
