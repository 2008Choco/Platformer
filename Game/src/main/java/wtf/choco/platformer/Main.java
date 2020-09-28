package wtf.choco.platformer;

public final class Main {

    private Main() { }

    public static void main(String[] args) {
        Game.get().start(); // Instantiates the game object and kicks off the process
    }

}
