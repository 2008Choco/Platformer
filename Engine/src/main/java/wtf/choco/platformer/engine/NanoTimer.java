package wtf.choco.platformer.engine;

/**
 * The internal game timer based on the system's nano time.
 */
public final class NanoTimer {

    private static final float RESOLUTION = 1.0F / 1_000_000_000L;

    private long start, last;
    private float deltaTime;
    private float fps, fpsLast;

    private float secondIncrement = 0.0F;

    public NanoTimer() {
        this.start = System.nanoTime();
    }

    /**
     * Get the time in nanoseconds when this timer was started (or last reset).
     *
     * @return the time since start
     */
    public long getTimeSinceStart() {
        return System.nanoTime() - start;
    }

    /**
     * Get the time since this timer was last {@link #update() updated}.
     *
     * @return the delta time
     */
    public float getDeltaTime() {
        return deltaTime;
    }

    /**
     * Get the assumed frames per second since this timer was last
     * {@link #update() updated}.
     *
     * @return the fps
     */
    public float getFps() {
        return fps;
    }

    /**
     * Get the assumed frames per second as of the last second. This value is
     * updated every second as opposed to {@link #getFps()} which is calculated
     * in real time based on approximation.
     *
     * @return the last fps
     */
    public float getFpsLast() {
        return fpsLast;
    }

    /**
     * Update this timer. This should NEVER be called by anything other than by
     * the engine's implementation.
     */
    public void update() {
        this.deltaTime = (getTimeSinceStart() - last) * RESOLUTION;
        this.fps = 1.0F / deltaTime;
        this.last = getTimeSinceStart();

        if ((secondIncrement += deltaTime) >= 1.0F) {
            this.secondIncrement = 0.0F;
            this.fpsLast = fps;
        }
    }

    /**
     * Reset this timer.
     */
    public void reset() {
        this.start = System.nanoTime();
        this.last = getTimeSinceStart();
    }

}
