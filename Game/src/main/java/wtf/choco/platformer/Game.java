package wtf.choco.platformer;

import wtf.choco.platformer.client.Keybinds;
import wtf.choco.platformer.client.listener.CursorListener;
import wtf.choco.platformer.client.render.PrimaryGameRenderer;
import wtf.choco.platformer.client.render.PrimaryRenderingContext;
import wtf.choco.platformer.engine.client.GameBase;
import wtf.choco.platformer.engine.client.RenderableWindow;
import wtf.choco.platformer.engine.client.keyboard.Keyboard;
import wtf.choco.platformer.engine.util.ImageUtils;
import wtf.choco.platformer.entity.Player;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.menu.GameMenu;
import wtf.choco.platformer.menu.gui.MainMenu;
import wtf.choco.platformer.sound.Sound;

public final class Game extends GameBase {

    public static final String VERSION = "v0.0.8";
    public static final String TITLE = "The Game With No Name";

    public Player player;
    public Level level;
    public GameMenu activeMenu;

    private static Game instance;

    private RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> window;

    public RenderableWindow<PrimaryRenderingContext, PrimaryGameRenderer> getWindow() {
        return window;
    }

    @Override
    public void init() {
        super.init();

        // Init the window (TODO: Move this to the engine)
        CursorListener mouseListener = new CursorListener(this);

        this.window = new RenderableWindow<>(TITLE + " - " + Game.VERSION, 1080, 720, new PrimaryGameRenderer(this));
        this.window.acceptListeners(component -> {
            component.addKeyListener(new Keyboard.Listener());
            component.addMouseListener(mouseListener);
            component.addMouseMotionListener(mouseListener);
        });
        this.window.setResizeCallback((width, height, newWidth, newHeight) -> {
            if (this.activeMenu == null) {
                return;
            }

            this.activeMenu.onUpdateWindow(window, width, height, newWidth, newHeight);
        });

        // Init game-specific code
        Keybinds.init();
        this.window.init();
        this.activeMenu = MainMenu.create(this);

        Level.create("level_1", ImageUtils.loadImage("/textures/levels/level.png"));
    }

    @Override
    public void render() {
        this.window.render();
    }

    @Override
    public void update(float deltaTime) {
        if (level != null) {
            this.level.update(deltaTime);
        }

        if (activeMenu != null) {
            this.activeMenu.update(deltaTime);
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
        Sound.releaseResources();
    }

    public void loadLevel(Level level) {
        this.level = level;
        this.player = level.getPlayer();
    }

    public static Game get() {
        return instance != null ? instance : (instance = new Game());
    }

    public static final class Debug {

        public static boolean debugInformation = true;
        public static boolean showHitboxes = false;

        private Debug() { }

    }

}
