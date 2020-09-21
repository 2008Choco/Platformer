package wtf.choco.platformer.menu.button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wtf.choco.platformer.client.render.PrimaryGameRenderer;

public abstract class GraphicGUIButton extends Button {

    private final BufferedImage display, hoveredDisplay;

    public GraphicGUIButton(int x, int y, int width, int height, String text, BufferedImage display, BufferedImage hoveredDisplay) {
        super(x, y, width, height, text);

        if (display == null) {
            throw new IllegalArgumentException("Display icon cannot be null");
        }

        this.display = display;
        this.hoveredDisplay = (hoveredDisplay != null ? hoveredDisplay : display);
    }

    public GraphicGUIButton(int x, int y, int width, int height, BufferedImage display, BufferedImage hoveredDisplay) {
        this(x, y, width, height, null, display, hoveredDisplay);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage((hovered ? hoveredDisplay : display), x, y, width, height, null);

        if (text != null) {
            graphics.setFont(PrimaryGameRenderer.FONT_COMICSANSMS_BOLD_29);
            graphics.setColor(textColour);
            graphics.drawString(text, x + ((width / 2) - (graphics.getFontMetrics().stringWidth(text) / 2)), y + ((height / 2) + (int) (graphics.getFontMetrics().getStringBounds(text, graphics).getHeight() / 4)));
        }
    }

}
