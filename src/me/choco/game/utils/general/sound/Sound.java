package me.choco.game.utils.general.sound;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {
	TEST("/testAudio.wav"),
	BUTTON_HOVER("/buttonHover.wav");
	
	private String path;
	private Sound(String path){
		this.path = path;
	}
	
	public AudioInputStream getAudioStream(){
		try{
			return AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(path));
		}catch (UnsupportedAudioFileException | IOException e){ e.printStackTrace(); return null; }
	}
	
	public static synchronized void playSound(Sound sound){
		Thread thread = new Thread(new Runnable(){
			public void run(){
				try{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = sound.getAudioStream();
					clip.open(inputStream);
					clip.start();
				}catch (IOException | LineUnavailableException e){ e.printStackTrace(); }
			}
		});
		thread.start();
		try { thread.join(); }
		catch (InterruptedException e) { e.printStackTrace(); }
	}
}