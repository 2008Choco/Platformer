package wtf.choco.platformer;

import wtf.choco.platformer.client.Window;
import wtf.choco.platformer.client.keybind.KeybindRegistry;
import wtf.choco.platformer.client.keybind.Keyboard;
import wtf.choco.platformer.client.listener.CursorListener;
import wtf.choco.platformer.client.render.PrimaryGameRenderer;
import wtf.choco.platformer.engine.client.render.IPrimaryRenderer;
import wtf.choco.platformer.entity.Player;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.menu.GameMenu;
import wtf.choco.platformer.menu.gui.MainMenu;
import wtf.choco.platformer.sound.Sound;
import wtf.choco.platformer.utils.ImageUtils;

public final class Game {

    public static final String VERSION = "v0.0.8";
    public static final String TITLE = "The Game with No Name";

    private static final int MAX_FPS = 120, MAX_TPS = 60;

    public Player player;
    public Level level;
    public GameMenu activeMenu;

    private static Game instance;

    private Thread thread;
    private boolean running;
    private int tps = 0;

    private final Window window;

    private Game() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));

        CursorListener mouseListener = new CursorListener(this);

        this.window = new Window(this, TITLE, 1080, 720);
        IPrimaryRenderer renderer = new PrimaryGameRenderer(this, window);
        this.window.bindRenderer(renderer);
        this.window.acceptListeners(component -> {
            component.addKeyListener(new Keyboard.Listener());
            component.addMouseListener(mouseListener);
            component.addMouseMotionListener(mouseListener);
        });

        this.start();
    }

    private void init() {
        KeybindRegistry.init();
        this.activeMenu = MainMenu.create(this);

        Level.create("level_1", ImageUtils.loadImage("/textures/levels/level.png"));
    }

    public void start() {
        if (thread == null) {
            this.thread = new Thread(this::run);
        }

        this.thread.start();
        this.running = true;
    }

    public void stop() {
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.running = false;
    }

    private void run() {
        this.init();

        long lastTime = System.nanoTime();
        double deltaTPS = 0, nsTPS = 1000000000 / MAX_TPS;
        double deltaFPS = 0, nsFPS = 1000000000 / MAX_FPS;
        long timer = System.currentTimeMillis();

        int ticks = 0;

        while (running) {
            long now = System.nanoTime();
            KeybindRegistry.pollInput();

            // Ticks
            deltaTPS += (now - lastTime) / nsTPS;
            while (deltaTPS >= 1) {
                this.tick();
                ticks++;
                deltaTPS--;
            }

            // Render
            deltaFPS += (now - lastTime) / nsFPS;
            while (deltaFPS >= 1) {
                this.window.render();
                deltaFPS--;
            }

            lastTime = now;

            // Per second calculations
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.window.saveFPS();
                this.tps = ticks;
                ticks = 0;
            }
        }

        this.stop();
    }

    private void tick() {
        if (level != null) {
            this.level.tick();
        }

        if (activeMenu != null) {
            this.activeMenu.tick();
        }
    }

    public Window getWindow() {
        return window;
    }

    public void loadLevel(Level level) {
        this.level = level;
        this.player = level.getPlayer();
    }

    public int getTPS() {
        return tps;
    }

    private void onShutdown() {
        Sound.releaseResources();
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
