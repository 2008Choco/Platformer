package wtf.choco.platformer.engine.client;

import wtf.choco.platformer.engine.NanoTimer;

/**
 * The base of every game for this engine. Every game should extend this base and
 * implement necessary methods. To start the game, {@link #start()} should be
 * invoked on the primary game thread.
 */
public abstract class GameBase {

    private NanoTimer timer;
    private float speed = 1.0F;

    private boolean shouldClose = false;

    /**
     * Start the game.
     */
    public final void start() {
        this.timer = new NanoTimer();

        // TODO: Move this initialization and game loop elsewhere
        this.init();

        do {
            this.update();

            // TODO: Synchronize the frame rate according to a target FPS. Thread#sleep() where necessary.
        } while (!shouldClose);

        this.shutdown();
    }

    /**
     * Get the game timer.
     *
     * @return the game timer
     */
    public NanoTimer getTimer() {
        return timer;
    }

    /**
     * Set the game speed.
     *
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get the game speed.
     *
     * @return current game speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Initialize this game. Any implementation overriding this method should call
     * its super before any other game initialization processing.
     */
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        this.timer.reset();
    }

    /**
     * Update the game. This should NEVER be called manually. Implementations should
     * override instead {@link #render()}.
     */
    public final void update() {
        this.timer.update();
        if (speed == 0.0F) {
            return;
        }

        float deltaTime = timer.getDeltaTime() * speed;
        this.update(deltaTime);

        this.render();
    }

    /**
     * Do a render pass. It is here that the game should be drawn to the screen.
     */
    public abstract void render();

    /**
     * Update the game. It is here that games should process entity movement, physics,
     * input among other things that require updating.
     *
     * @param deltaTime the time between now and the last successful update.
     */
    public abstract void update(float deltaTime);

    /**
     * Called before the game is shutdown.
     */
    public void shutdown() { }

}
