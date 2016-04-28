package me.choco.game.utils;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.choco.game.Game;
import me.choco.game.menus.GameMenu;
import me.choco.game.menus.utils.BackgroundHolder;
import me.choco.game.menus.utils.GUIButton;
import me.choco.game.utils.general.ImageLoader;

public class Window{
	
	public Window(int width, int height, String title, Game game){
		checkJavaVersion(1.8);
		
		JFrame frame = new JFrame(title + game.version.replace("Version", ""));
		
		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setIconImage(ImageLoader.loadImage("/icon.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.addComponentListener(new ComponentListener(){
			public void componentResized(ComponentEvent event){
				if (event.getComponent().getWidth() == Game.WIDTH && event.getComponent().getHeight() == Game.HEIGHT) return;
				Game.WIDTH = event.getComponent().getWidth();
				Game.HEIGHT = event.getComponent().getHeight();
				
				for (GameMenu menu : game.getMenuManager().getMenus()){
					for (GUIButton button : menu.getButtons())
						button.onUpdateWindow(event);
					
					if (menu instanceof BackgroundHolder){
						((BackgroundHolder) menu).getBackground().setPos(0, 0);
					}
				}
			}
			
			public void componentHidden(ComponentEvent event) {}
			public void componentMoved(ComponentEvent event) {}
			public void componentShown(ComponentEvent event) {}
		});
		
		frame.add(game);
		game.start();
	}
	
	private void checkJavaVersion(double requiredVersion){
		if (getJavaVersion() < requiredVersion){
			Object[] options = {"Do it later","Download Java " + requiredVersion};
			int choice = JOptionPane.showOptionDialog(null, "Java version " + getJavaVersion() + " detected!\n" + "Make sure you have JRE " + requiredVersion + " installed! This game will not work otherwise", 
					"Invalid Java Runtime Environment!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
			if (choice == 1){
				try {Desktop.getDesktop().browse(URI.create("http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html"));}
				catch (IOException e){e.printStackTrace();}
			}
			System.exit(1);
		}
	}
	
	private static double getJavaVersion(){
	    String version = System.getProperty("java.version");
	    int pos = version.indexOf('.');
	    pos = version.indexOf('.', pos+1);
	    return Double.parseDouble (version.substring (0, pos));
	}
}