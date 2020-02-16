package wtf.choco.platformer.client.keybind;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.menu.gui.MainMenu;
import wtf.choco.platformer.menu.gui.OptionsMenu;

public class KeybindRegistry {

    private static final List<Keybind> KEYBINDS = new ArrayList<>();

    public static final Keybind KEYBIND_SHOW_DEBUG_INFO = new Keybind(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK).onPress(() -> Game.Debug.debugInformation = !Game.Debug.debugInformation);
    public static final Keybind KEYBIND_SHOW_HITBOXES = new Keybind(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK).onPress(() -> Game.Debug.showHitboxes = !Game.Debug.showHitboxes);
    public static final Keybind KEYBIND_ESCAPE_MENU = new Keybind(KeyEvent.VK_ESCAPE).onPress(() -> {
        Game game = Game.get();
        if (game.activeMenu == null || game.activeMenu instanceof OptionsMenu) {
            game.activeMenu = MainMenu.create(game);
            game.level = null;
        }
    });


    public static void register(Keybind keybind) {
        if (keybind == null) {
            throw new IllegalArgumentException("Attempted to register null keybind");
        }

        KEYBINDS.add(keybind);
    }

    public static void forAllKeybinds(Consumer<Keybind> action) {
        KEYBINDS.forEach(action);
    }

    public static void forAllMatchingKeybinds(int key, int modifiers, Consumer<Keybind> action) {
        KEYBINDS.forEach(keybind -> {
            if (keybind.matches(key, modifiers)) {
                action.accept(keybind);
            }
        });
    }

    public static void init() {
        register(KEYBIND_SHOW_DEBUG_INFO);
        register(KEYBIND_SHOW_HITBOXES);
        register(KEYBIND_ESCAPE_MENU);
    }

}
