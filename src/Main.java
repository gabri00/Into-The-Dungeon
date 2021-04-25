import Essentials.Game;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Game game = new Game();
        game.initWorld(args);
        game.beginExploration();
    }

}