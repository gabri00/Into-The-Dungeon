package Essentials;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private int currFloor = 0;
    private int currChamber = 0;

    Random rand = new Random();
    final int nFloors = rand.nextInt(5) + 1 + 5;  // There can be between 5 and 10 floors
    final int nChambers = 3;


    // Maps each enemy type to an integer value
    private Map<Integer, Enemy> mapEnemies() {
        Map<Integer, Enemy> enemies = new HashMap<>();

        enemies.put(0, new Enemy("slime", 20.0, 10.0, 40.0, 0.0, 1, 10));           // 0 : slime
        enemies.put(1, new Enemy("goblin", 20.0, 10.0, 40.0, 0.0, 1, 15));          // 1 : goblin
        enemies.put(2, new Enemy("ogre", 250.0, 60.0, 40.0, 20.0, 2, 30));          // 2 : ogre
        enemies.put(3, new Enemy("demon", 250.0, 60.0, 40.0, 20.0, 2, 40));         // 3 : demon
        enemies.put(4, new Enemy("archdemon", 2000.0, 90.0, 50.0, 10.0, 3, 80));    // 4 : archdemon
        enemies.put(5, new Enemy("dragon", 2000.0, 90.0, 50.0, 10.0, 3, 120));      // 5 : dragon

        return enemies;
    }


    // Just print some basic stuff when the program starts
    public void initGame(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-h")) {
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
            else System.out.println("Argument " + args[0] + " is not valid");

            System.exit(0);
        }
        else {
            System.out.print("""
                    Welcome to the dungeon hero!
                    Let's begin your adventure...
                    
                    Please increase the console size for a better gaming experience!
                    """);
        }
    }


    // Create the dungeon matrix and assign an enemy to each chamber
    public void generateDungeon() {
        final var enemies = mapEnemies();

        Enemy[][] dungeon = new Enemy[nFloors][nChambers];

        System.out.println("Generating dungeon...\n" +
                           "The dungeon has " + nFloors + " floors");

        // Add an enemy to each chamber based on the enemy danger
        for (short i = 0; i < nFloors; i++) {
            for (short j = 0; j < nChambers; j++) {
                if (i < 1.0/3.0 * nFloors)
                    dungeon[i][j] = enemies.get(rand.nextInt(2));
                else if (i >= 1.0/3.0 * nFloors && i < 2.0/3.0 * nFloors)
                    dungeon[i][j] = enemies.get(rand.nextInt(2) + 2);
                else
                    dungeon[i][j] = enemies.get(rand.nextInt(2) + 4);
            }
        }

        // Print test
        for (int i = 0; i < nFloors; i++) {
            for (int j = 0; j < nChambers; j++) {
                var temp = dungeon[i][j];
                System.out.println("Floor " + (i+1) + ", chamber " + (j+1) + ", Enemy: " + temp.getTag());
            }
        }
    }


    // Choose the class of the hero and the weapon to use
    public void initHero() throws FileNotFoundException {
        System.out.print("First of all, you should choose a weapon if you don't want to get killed!\nWeapon: ");
        Scanner in = new Scanner(System.in);
        String weaponChoice = in.nextLine();
        weaponChoice = weaponChoice.toLowerCase();

        switch (weaponChoice) {
            case "sword" -> {
                Hero he = new Hero("warrior", 500, 50, 60, 10);
                Weapon sword = new Weapon("sword", 0.0, 22.5, 0.0, 5.0);
                he.equipWeapon(sword);
                he.loadTexture("assets/heroes/swordHero.txt");
            }
            case "spear" -> {
                Hero he = new Hero("lancer", 500.0, 50.0, 60.0, 10.0);
                Weapon spear = new Weapon("spear", 10.0, 30.0, 0.0, 1.0);
                he.equipWeapon(spear);
                he.loadTexture("assets/heroes/spearHero.txt");
            }
            case "bow" -> {
                Hero he = new Hero("archer", 300.0, 100.0, 20.0, 37.0);
                Weapon bow = new Weapon("bow", 20.0, 30.0, 0.0, 25.0);
                he.equipWeapon(bow);
                he.loadTexture("assets/heroes/archerHero.txt");
            }
            default -> throw new IllegalStateException("Unexpected value: " + weaponChoice);
        }
    }


    public byte actions() {
        Scanner in = new Scanner(System.in);
        String action;

        byte statusCode = 0;

        do {
            System.out.print("""
                    What would you like to do?
                    type 'e' to explore the next chamber/floor
                    type 'q' to quit the game
                    action:\s""");

            action = in.next().toLowerCase();

            if (action.equals("e")) statusCode = explore();
            else if (action.equals("q")) statusCode = quit();
        } while (!action.equals("e") && !action.equals("q"));

        return statusCode;
    }


    private byte explore() {
        currChamber++;

        if (currFloor == 0 || currChamber > 3) currFloor++;

        if (currChamber > 3)    currChamber = 1;

        if (currFloor == 1) System.out.println("Welcome to the first floor, chamber " + currChamber);
        else if (currFloor == nFloors) System.out.println("Welcome to the last floor, chamber " + currChamber);
        else System.out.println("Welcome to floor " + currFloor + ", chamber " + currChamber);

        if (currFloor >= nFloors && currChamber == 3)    return quit();

        return 1;
    }


    private byte quit() {
        System.out.println("bye");

        return 0;
    }

}
