package wtf.choco.platformer.client.listener;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Keybinds;
import wtf.choco.platformer.engine.client.keyboard.Keybind;
import wtf.choco.platformer.engine.client.keyboard.KeybindListener;
import wtf.choco.platformer.menu.gui.MainMenu;
import wtf.choco.platformer.menu.gui.OptionsMenu;

public final class KeybindListenerMisc implements KeybindListener {

    public static final KeybindListener INSTANCE = new KeybindListenerMisc();

    private KeybindListenerMisc() { }

    @Override
    public void onPress(Keybind keybind) {
        if (keybind == Keybinds.KEYBIND_SHOW_DEBUG_INFO) {
            Game.Debug.debugInformation = !Game.Debug.debugInformation;
        }

        else if (keybind == Keybinds.KEYBIND_SHOW_HITBOXES) {
            Game.Debug.showHitboxes = !Game.Debug.showHitboxes;
        }

        else if (keybind == Keybinds.KEYBIND_ESCAPE_MENU) {
            Game game = Game.get();
            if (game.activeMenu == null || game.activeMenu instanceof OptionsMenu) {
                game.activeMenu = MainMenu.create(game);
                game.level = null;
            }
        }
    }

}
