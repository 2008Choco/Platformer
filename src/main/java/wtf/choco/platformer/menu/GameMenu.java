package wtf.choco.platformer.menu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.engine.client.render.RenderingContext;
import wtf.choco.platformer.menu.button.Button;

public abstract class GameMenu {

    private final List<Button> buttons = new ArrayList<>();

    public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
        for (Button button : buttons) {
            if (mouseX >= button.getX()
                    && mouseX <= button.getX() + button.getWidth()
                    && mouseY >= button.getY()
                    && mouseY <= button.getY() + button.getHeight()) {
                button.onPress(mouseX, mouseY, mouseButton);
            }
        }
    }

    public void onMouseMove(int mouseX, int mouseY, int deltaX, int deltaY) {
        for (Button button : buttons) {
            int buttonX = button.getX(), buttonY = button.getY();
            int buttonWidth = button.getWidth(), buttonHeight = button.getHeight();
            int lastMouseX = mouseX - deltaX, lastMouseY = mouseY - deltaY;

            if (mouseX >= buttonX // Inside of button
                    && mouseX <= buttonX + buttonWidth
                    && mouseY >= buttonY
                    && mouseY <= buttonY + buttonHeight) {
                if (lastMouseX >= buttonX // Was inside of button last frame
                        && lastMouseX <= buttonX + buttonWidth
                        && lastMouseY >= buttonY
                        && lastMouseY <= buttonY + buttonHeight) {
                    button.onMouseHover(mouseX, mouseY, deltaX, deltaY);
                } else { // Was outside of button last frame (just entered the button)
                    button.onMouseEnter(mouseX, mouseY, deltaX, deltaY);
                }
            }

            else if (lastMouseX >= buttonX // Outside of button, was inside of button last frame
                    && lastMouseX <= buttonX + buttonWidth
                    && lastMouseY >= buttonY
                    && lastMouseY <= buttonY + buttonHeight) {
                button.onMouseExit(mouseX, mouseY, deltaX, deltaY);
            }
        }
    }

    public void onUpdateWindow(Window window, int oldSizeX, int oldSizeY, int newSizeX, int newSizeY) {
        this.buttons.forEach(b -> b.onWindowUpdate(window, oldSizeX, oldSizeY, newSizeX, newSizeY));
    }

    protected void addButton(Button button) {
        this.buttons.add(button);
    }

    public void tick() { }

    public void render(Graphics graphics, RenderingContext context) {
        this.renderBackground(graphics);
        this.buttons.forEach(b -> b.render(graphics));
        this.renderForeground(graphics);
    }

    /**
     * Called by the render function to render the background of this menu (before the buttons).
     *
     * @param graphics the graphics context
     */
    protected void renderBackground(Graphics graphics) { }

    /**
     * Called by the render function to render the foreground of this menu (after the buttons).
     *
     * @param graphics the graphics context
     */
    protected void renderForeground(Graphics graphics) { }

}
