package wtf.choco.platformer.client.listener;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.keybind.Keybind;
import wtf.choco.platformer.client.keybind.KeybindListener;
import wtf.choco.platformer.client.keybind.KeybindRegistry;

public final class KeybindListenerGameplay implements KeybindListener {

    public static final KeybindListener INSTANCE = new KeybindListenerGameplay();

    private static final float JUMP_POWER = 0.5F;
    private static final float SPEED = 0.25F;

    private KeybindListenerGameplay() { }

    @Override
    public void onPress(Keybind keybind) {
        Game game = Game.get();
        if (game.level == null || game.player == null) {
            return;
        }

        if (keybind == KeybindRegistry.KEYBIND_JUMP && game.player.getVelocityY() >= -0.01 && game.player.getVelocityY() <= 0.01) {
            game.player.setVelocityY(-SPEED * JUMP_POWER);
        }

        if (keybind == KeybindRegistry.KEYBIND_LEFT) {
            game.player.setVelocityX(-SPEED);
        }

        if (keybind == KeybindRegistry.KEYBIND_RIGHT) {
            game.player.setVelocityX(SPEED);
        }
    }

}
