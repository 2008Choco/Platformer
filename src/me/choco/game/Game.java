package me.choco.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import me.choco.game.menus.MenuManager;
import me.choco.game.menus.menu.MainMenu;
import me.choco.game.menus.menu.OptionsMenu;
import me.choco.game.utils.Background;
import me.choco.game.utils.Camera;
import me.choco.game.utils.EntityHandler;
import me.choco.game.utils.LevelManager;
import me.choco.game.utils.Window;
import me.choco.game.utils.general.ExceptionHandler;
import me.choco.game.utils.general.GameFont;
import me.choco.game.utils.general.ImageUtils;
import me.choco.game.utils.general.NumUtils;
import me.choco.game.utils.general.resources.Texture;
import me.choco.game.utils.listeners.ClickListener;
import me.choco.game.utils.listeners.KeyboardListener;
import me.choco.game.utils.listeners.MovementListener;

/**
 * This game was written as a school project started in the year of 2016. There is no
 * intention of releasing the game publicly for playability, nor allowing others to
 * play the game (excluding those allowed to test it for Alpha, Beta, etc. testing phases)
 * <br><br>
 * The intention of the game is to create a type of Platformer with one singular main
 * character, a couple of enemies that will attempt to kill said player, and varying blocks
 * or tiles throughout the world. Sub programs may also come bundled with the game to help
 * with the creation of the game in a much simpler way.
 * <br>
 * <br> <b>- Legal Information -</b>
 * <br>
 * <br> Copyright (C) 2016 Parker Hawke
 * <br> 
 * <br> This program is free software: you can redistribute it and/or modify
 * <br> it under the terms of the GNU General Public License as published by
 * <br> the Free Software Foundation, either version 3 of the License, or
 * <br> (at your option) any later version.
 * <br> 
 * <br> This program is distributed in the hope that it will be useful,
 * <br> but WITHOUT ANY WARRANTY; without even the implied warranty of
 * <br> MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * <br> GNU General Public License for more details.
 * <br> 
 * <br> You should have received a copy of the GNU General Public License
 * <br> along with this program.  If not, see {@link http://www.gnu.org/licenses/}
 * 
 * @author <b>Parker Hawke</b> - Game Developer
 * @author <b>Jenna Young</b> - Texture Artist
 */
public class Game extends Canvas implements Runnable{
	public final String version = "Version 0.06 Alpha - Tile Collision Update";
	public static int WIDTH = 720, HEIGHT = WIDTH / 9 * 6;
	public static final String title = "The Game with No Name";

	private static Game instance;
	private static Camera camera;
	public static int currentFPS = 0;
	
	private static final long serialVersionUID = -2568288252169912698L;
	private boolean debugMode = true;
	
	private final EntityHandler entityHandler;
	private final MenuManager menuManager;
	private final KeyboardListener keyListener;
	private final ClickListener mouseListener;
	private final MovementListener movementListener;
	private final LevelManager levelManager;
	
	private Thread thread;
	private boolean running;
	
	private GameState state = GameState.MAIN_MENU;
	
	public enum GameState{
		GAME, MAIN_MENU, OPTIONS_MENU;
	}
	
	public Game(){
		setFocusable(true); requestFocus();
		instance = this;
		camera = new Camera(this);
		
		entityHandler = new EntityHandler(this);
		menuManager = new MenuManager();
		keyListener = new KeyboardListener(this);
		mouseListener = new ClickListener(this);
		movementListener = new MovementListener(this);
		levelManager = new LevelManager(this);
		
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(movementListener);
		
		new Window(WIDTH, HEIGHT, Game.title, this);
	}
	
	public void init(){
		/* LOAD OBJECTS, IMAGES, ETC */
		menuManager.addMenu(new MainMenu(this, new Background(Texture.GUI_BACKGROUND_MAIN.getTexture(), -0.5, 0)));
		menuManager.addMenu(new OptionsMenu(this));
		
		levelManager.loadLevel("Level 1", ImageUtils.loadImage("/game/levels/level1.png"));
		levelManager.setCurrentLevel(0);
		
		camera.start();
		
//		entityHandler.addObject(new Enemy(new Location(200, 200), 30, 30));
	}
	
	public synchronized void start(){
		if (thread == null) thread = new Thread(this);
		thread.start();
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
		entityHandler.tick();
		menuManager.tickForState(state);
		levelManager.tick();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){ this.createBufferStrategy(3); return;}
		
		Graphics graphics = bs.getDrawGraphics();
		
		if (state.equals(GameState.GAME)){
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, WIDTH, HEIGHT);
			
			levelManager.render(graphics);
			entityHandler.render(graphics);
		}
		menuManager.renderForState(state, graphics);
		
		if (debugMode){
			graphics.setFont(GameFont.ARIAL_BOLD_16.getFont());
			graphics.setColor(currentFPS > 30 ? Color.YELLOW : Color.RED);
			graphics.drawString("FPS: " + currentFPS, 5, 16);
		}
		
		graphics.setFont(graphics.getFont().deriveFont(Font.ITALIC, 10F));
		graphics.setColor(Color.GRAY);
		graphics.drawString(version, (WIDTH / 2) - NumUtils.center(graphics, version), 10);
		
		bs.show();
		graphics.dispose();
	}
	
	public Thread getMainThread(){
		return thread;
	}
	
	public EntityHandler getEntityHandler(){
		return entityHandler;
	}
	
	public MenuManager getMenuManager(){
		return menuManager;
	}
	
	public KeyboardListener getKeyListener(){
		return keyListener;
	}
	
	public LevelManager getLevelManager(){
		return levelManager;
	}
	
	public void setDebugMode(boolean debugMode){
		this.debugMode = debugMode;
	}
	
	public boolean isInDebugMode(){
		return debugMode;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void setState(GameState state){
		this.state = state;
	}
	
	public GameState getState(){
		return state;
	}
	
	public static Game getGame(){
		return instance;
	}
	
	public static Camera getCamera(){
		return camera;
	}
	
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
		new Game();
	}
}