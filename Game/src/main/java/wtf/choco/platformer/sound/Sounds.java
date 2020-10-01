package wtf.choco.platformer.sound;

import wtf.choco.platformer.registry.Registry;

public final class Sounds {

    public static final Sound GUI_BUTTON_HOVER = register("gui.button.button_hover", new Sound("gui/button/button_hover"));
    public static final Sound TILE_BUSH_WALK = register("tile.bush_walk", new Sound("tile/bush_walk"));

    private Sounds() { }

    private static Sound register(String id, Sound sound) {
        return Registry.SOUND.register(id, sound);
    }

}
