package wtf.choco.platformer.menu.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.client.render.PrimaryGameRenderer;

public abstract class Button {

    protected boolean hovered = false;

    protected int x, y;
    protected final int width, height;
    protected String text;

    protected final Color backgroundColour, hoverColour, textColour;

    public Button(int x, int y, int width, int height, String text, Color backgroundColour, Color hoverColour, Color textColour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;

        this.backgroundColour = backgroundColour;
        this.hoverColour = hoverColour;
        this.textColour = textColour;
    }

    public Button(int x, int y, int width, int height, String text) {
        this(x, y, width, height, text, Color.GRAY, Color.DARK_GRAY, Color.WHITE);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isHovered() {
        return hovered;
    }

    /**
     * Called when this button is pressed by the user.
     *
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     * @param mouseButton the mouse button used to press the button. Will be one of either
     * <ul>
     *   <li>{@link MouseEvent#BUTTON1}
     *   <li>{@link MouseEvent#BUTTON2}
     *   <li>{@link MouseEvent#BUTTON3}
     * </ul>
     */
    public void onPress(int mouseX, int mouseY, int mouseButton) { }

    /**
     * Called once when the cursor has entered the bounds of this button.
     *
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     * @param deltaX the change in the x position of the mouse since the last frame
     * @param deltaY the change in the y position of the mouse since the last frame
     */
    public void onMouseEnter(int mouseX, int mouseY, float deltaX, float deltaY) {
        this.hovered = true;
    }

    /**
     * Called once when the cursor has exited the bounds of this button.
     *
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     * @param deltaX the change in the x position of the mouse since the last frame
     * @param deltaY the change in the y position of the mouse since the last frame
     */
    public void onMouseExit(int mouseX, int mouseY, float deltaX, float deltaY) {
        this.hovered = false;
    }

    /**
     * Called once when the cursor has moved within the bounds of this button.
     *
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     * @param deltaX the change in the x position of the mouse since the last frame
     * @param deltaY the change in the y position of the mouse since the last frame
     */
    public void onMouseHover(int mouseX, int mouseY, float deltaX, float deltaY) {
        this.hovered = true;
    }

    /**
     * Called once when the window updates. This should be used to rescale or reposition the
     * button according to the new sizing of the window.
     *
     * @param window the window being updated
     * @param oldWidth the old window width
     * @param oldHeight the old window height
     * @param width the new window width
     * @param height the new window height
     */
    public void onWindowUpdate(Window window, int oldWidth, int oldHeight, int width, int height) { }

    public void render(Graphics graphics) {
        graphics.setColor(hovered ? hoverColour : backgroundColour);
        graphics.fillRect(x, y, width, height);

        if (text != null) {
            graphics.setFont(PrimaryGameRenderer.FONT_COMICSANSMS_BOLD_29);
            graphics.setColor(textColour);
            graphics.drawString(text, x + ((width / 2) - (graphics.getFontMetrics().stringWidth(text) / 2)), y + ((height / 2) + (int) (graphics.getFontMetrics().getStringBounds(text, graphics).getHeight() / 4)));
        }
    }

}
