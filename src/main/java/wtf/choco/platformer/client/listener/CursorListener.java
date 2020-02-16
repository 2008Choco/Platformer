package wtf.choco.platformer.client.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.menu.GameMenu;

public class CursorListener implements MouseListener, MouseMotionListener {

    private int lastMouseX, lastMouseY;

	private final Game game;

	public CursorListener(Game game) {
		this.game = game;
	}

    @Override
    public void mouseClicked(MouseEvent event) {
        GameMenu menu = game.getActiveMenu();
        if (menu != null) {
            menu.onMouseClick(event.getX(), event.getY(), event.getButton());
        }
    }

	@Override
    public void mouseMoved(MouseEvent event) {
        GameMenu menu = game.getActiveMenu();
        if (menu != null) {
            int x = event.getX(), y = event.getY();
            menu.onMouseMove(x, y, x - lastMouseX, y - lastMouseY);

            this.lastMouseX = x;
            this.lastMouseY = y;
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) { } // Unused

    @Override
    public void mouseExited(MouseEvent event) { } // Unused

    @Override
    public void mousePressed(MouseEvent event) { } // Unused

    @Override
    public void mouseReleased(MouseEvent event) { } // Unused

    @Override
    public void mouseDragged(MouseEvent event) { } // Unused

}