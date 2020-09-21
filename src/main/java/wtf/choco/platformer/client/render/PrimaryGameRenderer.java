package wtf.choco.platformer.client.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.engine.client.render.IPrimaryRenderer;
import wtf.choco.platformer.engine.client.render.RenderingContext;
import wtf.choco.platformer.utils.Location;
import wtf.choco.platformer.utils.NumberUtils;

public final class PrimaryGameRenderer implements IPrimaryRenderer {

    public static final Font FONT_ARIAL_BOLD_16 = new Font("Arial", Font.BOLD, 16);
    public static final Font FONT_COMICSANSMS_BOLD_29 = new Font("Comic Sans MS", Font.BOLD, 29);

    private static final DecimalFormat COORDINATE_FORMAT = new DecimalFormat("0.00");

    private final Game game;
    private final Window window;

    public PrimaryGameRenderer(Game game, Window window) {
        this.game = game;
        this.window = window;
    }

    @Override
    public void init(RenderingContext context) {
        // Here, you would register custom renderers to the rendering context
    }

    @Override
    public void render(Graphics graphics, RenderingContext context) {
        if (game.level != null) {
            this.game.level.render(graphics, context);
        }

        if (game.activeMenu != null) {
            this.game.activeMenu.render(graphics, context);
        }

        if (Game.Debug.debugInformation) {
            graphics.setFont(FONT_ARIAL_BOLD_16);
            graphics.setColor(window.getFPS() >= 60 ? Color.GREEN : (window.getFPS() >= 30 ? Color.YELLOW : Color.RED));
            graphics.drawString("FPS: " + window.getFPS(), 5, 16);

            graphics.setColor(Color.YELLOW);
            graphics.drawString("TPS: " + game.getTPS(), 5, 32);

            // Player debug information
            if (game.player != null) {
                graphics.setFont(FONT_ARIAL_BOLD_16);
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
    }

}
