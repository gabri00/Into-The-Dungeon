import Essentials.Game;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Game game = new Game();
        game.initGame(args);
        game.initHero();
        game.generateDungeon();

        System.out.println("Finally we are going into the dungeon! Good luck and have fun!");
        while (game.actions() != 0) {

        }
    }

}