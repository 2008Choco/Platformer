package wtf.choco.platformer.menu.gui;

import java.awt.Color;
import java.awt.Graphics;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.render.PrimaryGameRenderer;
import wtf.choco.platformer.client.render.PrimaryRenderingContext;
import wtf.choco.platformer.client.render.Textures;
import wtf.choco.platformer.engine.client.RenderableWindow;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.menu.Background;
import wtf.choco.platformer.menu.GameMenu;
import wtf.choco.platformer.menu.button.GraphicGUIButton;
import wtf.choco.platformer.sound.Sound;
import wtf.choco.platformer.utils.NumberUtils;

public class MainMenu extends GameMenu {

    private final Background background;
    private final Game game;

    public MainMenu(Game game, Background background) {
        this.background = background;
        this.game = game;

        var window = game.getWindow();

        this.addButton(new GraphicGUIButton((window.getWidth() / 2) - 100, (window.getHeight() / 2) - 75, 200, 50, "Play", Textures.GUI_BUTTON_BACKGROUND.asImage(), Textures.GUI_BUTTON_HOVERED.asImage()) {

            @Override
            public void onMouseEnter(int mouseX, int mouseY, float deltaX, float deltaY) {
                super.onMouseEnter(mouseX, mouseY, deltaX, deltaY);
                Sound.GUI_BUTTON_HOVER.play();
            }

            @Override
            public void onPress(int mouseX, int mouseY, int mouseButton) {
                game.activeMenu = null;
                game.loadLevel(Level.getByName("level_1"));
            }

            @Override
            public void onWindowUpdate(RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> window, int oldWidth, int oldHeight, int width, int height) {
                this.setX((width / 2) - (getWidth() / 2));
                this.setY((height / 2) - (getHeight() / 2) - 75);
            }

        });

        this.addButton(new GraphicGUIButton((window.getWidth() / 2) - 100, window.getHeight() / 2, 200, 50, "Options", Textures.GUI_BUTTON_BACKGROUND.asImage(), Textures.GUI_BUTTON_HOVERED.asImage()) {

            @Override
            public void onMouseEnter(int mouseX, int mouseY, float deltaX, float deltaY) {
                super.onMouseEnter(mouseX, mouseY, deltaX, deltaY);
                Sound.GUI_BUTTON_HOVER.play();
            }

            @Override
            public void onPress(int mouseX, int mouseY, int mouseButton) {
                game.activeMenu = new OptionsMenu(game);
            }

            @Override
            public void onWindowUpdate(RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> window, int oldWidth, int oldHeight, int width, int height) {
                this.setX((width / 2) - (getWidth() / 2));
                this.setY((height / 2) - (getHeight() / 2));
            }

        });

        this.addButton(new GraphicGUIButton((window.getWidth() / 2) - 100, (window.getHeight() / 2) + 75, 200, 50, "Quit", Textures.GUI_BUTTON_BACKGROUND.asImage(), Textures.GUI_BUTTON_HOVERED.asImage()) {

            @Override
            public void onMouseEnter(int mouseX, int mouseY, float deltaX, float deltaY) {
                super.onMouseEnter(mouseX, mouseY, deltaX, deltaY);
                Sound.GUI_BUTTON_HOVER.play();
            }

            @Override
            public void onPress(int mouseX, int mouseY, int mouseButton) {
                System.exit(0);
            }

            @Override
            public void onWindowUpdate(RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> window, int oldWidth, int oldHeight, int width, int height) {
                this.setX((width / 2) - (getWidth() / 2));
                this.setY((height / 2) - (getHeight() / 2) + 75);
            }

        });
    }

    public Background getBackground() {
        return background;
    }

    @Override
    public void tick() {
        this.background.tick();
    }

    @Override
    protected void renderBackground(Graphics graphics) {
        this.background.render(graphics);
    }

    @Override
    protected void renderForeground(Graphics graphics) {
        graphics.setFont(PrimaryGameRenderer.FONT_COMICSANSMS_BOLD_29);
        graphics.setColor(Color.WHITE);

        graphics.drawString(Game.TITLE, (game.getWindow().getWidth() / 2) - NumberUtils.center(graphics, Game.TITLE), (game.getWindow().getHeight() / 2) - 125);
    }

    @Override
    public void onUpdateWindow(RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> window, int oldSizeX, int oldSizeY, int newSizeX, int newSizeY) {
        super.onUpdateWindow(window, oldSizeX, oldSizeY, newSizeX, newSizeY);
        this.background.reset();
    }

    public static MainMenu create(Game game) {
        return new MainMenu(game, new Background(game, Textures.GUI_BACKGROUND_MAIN.asImage(), -0.5F, 0.0F));
    }

}
