package wtf.choco.platformer.client.listener;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Keybinds;
import wtf.choco.platformer.engine.client.keyboard.Keybind;
import wtf.choco.platformer.engine.client.keyboard.KeybindListener;

public final class KeybindListenerGameplay implements KeybindListener {

    public static final KeybindListener INSTANCE = new KeybindListenerGameplay();

    private static final float JUMP_POWER = 500.0F;
    private static final float SPEED = 100.0F;

    private KeybindListenerGameplay() { }

    @Override
    public void onPress(Keybind keybind) {
        Game game = Game.get();
        if (game.level == null || game.player == null) {
            return;
        }

        if (keybind == Keybinds.KEYBIND_JUMP && !game.player.isAirborn()) {
            game.player.setVelocityY(-JUMP_POWER);
        }

        if (keybind == Keybinds.KEYBIND_LEFT) {
            game.player.setVelocityX(-SPEED);
        }

        if (keybind == Keybinds.KEYBIND_RIGHT) {
            game.player.setVelocityX(SPEED);
        }
    }

}
