import Core.Game;

import java.io.IOException;

public class Main {

    // Prints some basic instructions
    public static void printInstructions() {
        System.out.println("""
                Some basic guidelines for the game:
                At the very beginning you will have to choose a class for your hero, you can choose between:
                    - archer hero
                    - sword hero
                    - spear hero
                Every hero begins with different stats and it also gets an upgrade based on the chosen weapon.
                
                After the hero selection you will begin the exploration of the dungeon, the latter will have n floors with 3 chambers for each floor.
                In each room you will find an enemy waiting for you. Of course the fist floors contain easier monster, such as slimes and goblins.
                Then, lower in the dungeon, you will encounter stronger enemies like ogre and demons.
                If you've passed even this levels then you can go even lower in the dungeon and find incredibly strong monsters, such as archdemons and dragons!
                
                Each enemy will drop experience (useful for leveling up your character), and maybe even an artifact.
                Artifacts can be used to upgrade your hero's stats to become even stronger!
                There are 3 types of artifacts:
                    - armlets (can upgrade ATK, DEF and CR)
                    - rings (can upgrade ATK and CR)
                    - necklets (can upgrade HP and DEF)
                
                >> Please note that at the current version of the game artifacts and weapons cannot be upgraded <<
                
                At the end of each fight the game progress will be saved in a log file, so you can resume the exploration whenever you want.
                
                Enjoy this simple game and please don't be afraid of making pull requests to to the github repo :)
                """);
    }

    // Runs the game and checks for arguments
    public static void main(String[] args) throws IOException {
        Game game = new Game();

        if (args.length > 0) {
            if (args[0].equals("-l") || args[0].equals("--load"))
                game = game.loadGame();
            else {
                if (args[0].equals("-h") || args[0].equals("--help"))
                    printInstructions();
                else System.out.println("Argument " + args[0] + " doesn't exist");

                System.exit(0);
            }
        }
        else {
            System.out.print("""
                Welcome to the dungeon! Let's begin your adventure...
                Please increase the console size for a better gaming experience!\n
                """);
            game.initHero();
            System.out.println();
            game.generateDungeon();
        }

        System.out.println("Finally we are going into the dungeon! Good luck and have fun!\n");
        while (game.actions() != 0) {
            System.out.println();
            game.autoSave(game);
        }
    }

}