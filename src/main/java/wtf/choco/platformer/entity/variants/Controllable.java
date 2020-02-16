package wtf.choco.platformer.entity.variants;

import java.awt.event.KeyEvent;

import wtf.choco.platformer.client.listener.KeyboardListener;

public interface Controllable {
	public void onPressKey(KeyEvent event, KeyboardListener listener);

	public void onReleaseKey(KeyEvent event, KeyboardListener listener);
}