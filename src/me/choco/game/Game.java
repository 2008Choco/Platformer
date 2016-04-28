package me.choco.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.choco.game.entity.Player;
import me.choco.game.menus.MainMenu;
import me.choco.game.menus.MenuManager;
import me.choco.game.menus.OptionsMenu;
import me.choco.game.utils.EntityHandler;
import me.choco.game.utils.Window;
import me.choco.game.utils.general.ExceptionHandler;
import me.choco.game.utils.general.GameFont;
import me.choco.game.utils.general.ImageLoader;
import me.choco.game.utils.listeners.ClickListener;
import me.choco.game.utils.listeners.KeyboardListener;
import me.choco.game.utils.listeners.MovementListener;
import me.choco.game.utils.tilemaps.Background;

public class Game extends Canvas implements Runnable{
	public final String version = "Version 0.03 Alpha - Relativity Update";
	public static int WIDTH = 720, HEIGHT = WIDTH / 9 * 6;
	
	private int currentFPS = 0;
	
	private static final long serialVersionUID = -2568288252169912698L;
	private boolean debugMode = true;
	
	private final EntityHandler handler;
	private final MenuManager menuManager;
	private final KeyboardListener keyListener;
	private final ClickListener mouseListener;
	private final MovementListener movementListener;
	
	private Thread thread;
	private boolean running;
	
	private GameState state = GameState.MAIN_MENU;
	
	public enum GameState{
		GAME, MAIN_MENU, OPTIONS_MENU;
	}
	
	public Game(){
		setFocusable(true); requestFocus();
		handler = new EntityHandler();
		menuManager = new MenuManager();
		keyListener = new KeyboardListener(this);
		mouseListener = new ClickListener(this);
		movementListener = new MovementListener(this);
		
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(movementListener);
		
		new Window(WIDTH, HEIGHT, "Platformer", this);
	}
	
	public void init(){
		/* LOAD OBJECTS, IMAGES, ETC */
		menuManager.addMenu(new MainMenu(this, new Background(ImageLoader.loadImage("/background.png"), -0.5, 0)));
		menuManager.addMenu(new OptionsMenu(this));
		
		handler.addObject(new Player(100, 100));
	}

	public synchronized void start(){
		if (thread == null) thread = new Thread(this);
		thread.start();
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
		}catch (InterruptedException e){ e.printStackTrace(); }
		running = false;
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		double delta = 0, amountOfTicks = 60.0;
		double ns = 1_000_000_000 / amountOfTicks;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				/* System.out.println("FPS: " + frames) - Print out FPS to console */
				currentFPS = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick(){
		handler.tick();
		menuManager.tickForState(state);
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){ this.createBufferStrategy(3); return;}
		
		Graphics graphics = bs.getDrawGraphics();
		
		if (state.equals(GameState.GAME)){
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, WIDTH, HEIGHT);
			
			handler.render(graphics);
		}
		menuManager.renderForState(state, graphics);
		
		graphics.setFont(GameFont.ARIAL_BOLD_16.getFont());
		if (debugMode){
			graphics.setColor(currentFPS > 30 ? Color.YELLOW : Color.RED);
			graphics.drawString("FPS: " + currentFPS, 5, 16);
		}
		
		graphics.setFont(graphics.getFont().deriveFont(Font.ITALIC, 10F));
		graphics.setColor(Color.GRAY);
		graphics.drawString(version, (WIDTH / 2) - (graphics.getFontMetrics().stringWidth(version) / 2), 10);
		
		bs.show();
		graphics.dispose();
	}
	
	public EntityHandler getEntityHandler(){
		return handler;
	}
	
	public MenuManager getMenuManager(){
		return menuManager;
	}
	
	public KeyboardListener getKeyListener(){
		return keyListener;
	}
	
	public void toggleDebugMode(){
		this.debugMode = !debugMode;
	}
	
	public boolean isInDebugMode(){
		return debugMode;
	}
	
	public void setState(GameState state){
		this.state = state;
	}
	
	public GameState getState(){
		return state;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}