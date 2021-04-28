import Essentials.Game;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Game game = new Game();
        game.initGame(args);
        System.out.println();

        game.initHero();
        System.out.println();

        game.generateDungeon();
        System.out.println();

        System.out.println("Finally we are going into the dungeon! Good luck and have fun!\n");
        while (game.actions() != 0) System.out.println();
    }

}