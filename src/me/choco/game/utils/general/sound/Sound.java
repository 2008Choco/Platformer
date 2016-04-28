package me.choco.game.utils.general.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {
	BUTTON_HOVER(Sound.class.getResource("/buttonHover.wav"));
	
	private URL resourceURL;
	private Sound(URL resourceURL){	
		this.resourceURL = resourceURL;
	}
	
	public AudioInputStream getAudioStream(){
		try{
			return AudioSystem.getAudioInputStream(resourceURL);
		}catch (UnsupportedAudioFileException | IOException e){ e.printStackTrace(); return null; }
	}
	
	
	/* Utility methods and fields */
	
	private static final Mixer mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[0]);
	private static final DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
	
	public static synchronized void playSound(Sound sound){
		try{
			Clip clip = (Clip) mixer.getLine(dataInfo);
			clip.open(sound.getAudioStream());
			clip.start();
			
			clip.addLineListener(new LineListener() {
				public void update(LineEvent evt) {
					if (evt.getType() == LineEvent.Type.STOP)
						evt.getLine().close();
				}
			});
		}catch(LineUnavailableException | IOException e){ e.printStackTrace(); }
	}
}