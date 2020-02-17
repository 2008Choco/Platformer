package wtf.choco.platformer.menu.gui;

import java.awt.Color;
import java.awt.Graphics;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.client.render.RenderManager;
import wtf.choco.platformer.client.render.texture.Texture;
import wtf.choco.platformer.menu.GameMenu;
import wtf.choco.platformer.menu.button.GraphicGUIButton;
import wtf.choco.platformer.sound.Sound;
import wtf.choco.platformer.utils.NumberUtils;

public class OptionsMenu extends GameMenu {

    private final Game game;

	public OptionsMenu(Game game) {
	    this.game = game;

	    Window window = game.getWindow();

	    this.addButton(
			new GraphicGUIButton((window.getWidth() / 2) - 100, (window.getHeight() / 2) - 25, 200, 50, "Return", Texture.GUI_BUTTON_BACKGROUND.asImage(), Texture.GUI_BUTTON_HOVERED.asImage()) {

			    @Override
                public void onMouseEnter(int mouseX, int mouseY, float deltaX, float deltaY) {
			        super.onMouseEnter(mouseX, mouseY, deltaX, deltaY);
	                Sound.GUI_BUTTON_HOVER.play();
			    }

			    @Override
                public void onPress(int mouseX, int mouseY, int mouseButton) {
			        game.activeMenu = MainMenu.create(game);
			    }

			    @Override
                public void onWindowUpdate(Window window, int oldWidth, int oldHeight, int width, int height) {
			        this.setX((width / 2) - (getWidth() / 2));
			        this.setY((height / 2) - (getHeight() / 2));
			    }

			}
		);
	}

	@Override
	protected void renderBackground(Graphics graphics) {
	    Window window = game.getWindow();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, window.getWidth(), window.getHeight());
	}

	@Override
	protected void renderForeground(Graphics graphics) {
	    Window window = game.getWindow();

        graphics.setColor(Color.WHITE);
        graphics.setFont(RenderManager.FONT_ARIAL_BOLD_16.deriveFont(24F));

        String string = "Options are a work in progress";
        graphics.drawString(string, (window.getWidth() / 2) - NumberUtils.center(graphics, string), (window.getHeight() / 2) - 50);
	}

}