package wtf.choco.platformer.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import wtf.choco.platformer.registry.Registry;

public class Sound {

	private Clip clip = null;

	Sound(String path) {
	    path = "/sounds/" + path.concat(".wav");

		try {
		    InputStream audioInputStream = Sound.class.getResourceAsStream(path);
		    if (audioInputStream == null) {
		        throw new IllegalAccessError("Could not find audio file at path \"" + path + "\"");
		    }

		    this.clip = AudioSystem.getClip();
	        this.clip.open(AudioSystem.getAudioInputStream(audioInputStream));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
	}

	public void play() {
	    if (clip == null) {
	        System.err.println("Cannot play sound as the clip is invalid");
	        return;
	    }

	    this.stop();
	    this.clip.setFramePosition(0);
	    this.clip.start();
	}

	public void stop() {
	    if (clip.isActive()) {
	        this.clip.stop();
	        this.clip.setFramePosition(0);
	    }
	}

	public void release() {
	    if (clip != null) {
	        this.stop();
	        this.clip.close();
	    }
	}

	public static void releaseResources() {
	    Registry.SOUND.forEach(Sound::release);
	}

}