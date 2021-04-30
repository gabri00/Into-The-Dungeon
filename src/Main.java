import Core.Game;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();

        game.initGame(args);
        System.out.println();

        game.initHero();
        System.out.println();

        game.generateDungeon();
        System.out.println();

        System.out.println("Finally we are going into the dungeon! Good luck and have fun!\n");
        while (game.actions() != 0) {
            System.out.println();
            game.autoSave();
        }
    }

}