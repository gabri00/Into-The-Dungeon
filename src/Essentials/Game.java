package Essentials;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {

    Random rand = new Random();
    Scanner in = new Scanner(System.in);

    private final int nFloors = 10, nChambers = 3;

    private int currFloor = 0, currChamber = 0;

    private Enemy[][] dungeon = new Enemy[nFloors][nChambers];
    private Hero he;


    // Maps each enemy type to an integer value
    private Map<Integer, Enemy> mapEnemies() {
        Map<Integer, Enemy> enemies = new HashMap<>();

        enemies.put(0, new Enemy("slime", 20, 10, 10, 0, 1, 10));           // 0 : slime
        enemies.put(1, new Enemy("goblin", 20, 10, 10, 0, 1, 15));          // 1 : goblin
        enemies.put(2, new Enemy("ogre", 250, 60, 10, 20, 2, 30));          // 2 : ogre
        enemies.put(3, new Enemy("demon", 250, 60, 10, 20, 2, 40));         // 3 : demon
        enemies.put(4, new Enemy("archdemon", 2000, 90, 20, 10, 3, 80));    // 4 : archdemon
        enemies.put(5, new Enemy("dragon", 2000, 90, 20, 10, 3, 120));      // 5 : dragon

        return enemies;
    }


    // Maps each hero class to a string identifier
    private Map<String, Hero> mapHeroes() {
        Map<String, Hero> heroes = new HashMap<>();

        heroes.put("warrior", new Hero("warrior", 20, 10, 10, 0));
        heroes.put("lancer", new Hero("lancer", 20, 10, 10, 0));
        heroes.put("archer", new Hero("archer", 250, 60, 10, 20));

        return heroes;
    }


    // Maps each weapon type to a string identifier
    private Map<String, Weapon> mapWeapons() {
        Map<String, Weapon> weapons = new HashMap<>();

        weapons.put("sword", new Weapon("sword", 20, 10, 10, 0));
        weapons.put("spear", new Weapon("spear", 20, 10, 10, 0));
        weapons.put("bow", new Weapon("bow", 250, 60, 10, 20));

        return weapons;
    }

    // Maps each artifact type to an integer value
    private Map<Integer, Artifact> mapArtifacts() {
        Map<Integer, Artifact> artifacts = new HashMap<>();

        artifacts.put(0, new Artifact("armlet", 20, 10, 10, 0));
        artifacts.put(1, new Artifact("ring", 20, 10, 10, 0));
        artifacts.put(2, new Artifact("necklet", 250, 60, 10, 20));

        return artifacts;
    }


    // Just print some basic instructions when the game starts
    public void initGame(final String[] args) {
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
        else System.out.print("""
                Welcome to the dungeon! Let's begin your adventure...
                Please increase the console size for a better gaming experience!
                """);
    }


    // Create the dungeon matrix and assign an enemy to each chamber
    public void generateDungeon() {
        final var enemies = mapEnemies();

        // dungeon = new Enemy[nFloors][nChambers];

        System.out.printf("Generating dungeon... The dungeon has %d floors and %d rooms per floor%n", nFloors, nChambers);

        // Add an enemy to each chamber based on the enemy danger, (approximately) one third for each danger zone
        for (byte i = 0; i < nFloors; i++) {
            for (byte j = 0; j < nChambers; j++) {
                if (i < 1.0/3.0 * nFloors)
                    dungeon[i][j] = enemies.get(rand.nextInt(2));
                else if (i >= 1.0/3.0 * nFloors && i < 2.0/3.0 * nFloors)
                    dungeon[i][j] = enemies.get(rand.nextInt(2) + 2);
                else
                    dungeon[i][j] = enemies.get(rand.nextInt(2) + 4);
            }
        }
    }


    // Choose the class of the hero and the weapon to use
    public void initHero() throws FileNotFoundException {
        final var weapons = mapWeapons();
        final var heroTypes = mapHeroes();


        System.out.print("First of all, you should choose a weapon if you don't want to get killed!\nWhat do you want to pick (sword/bow/spear)? ");
        String weaponChoice;

        do weaponChoice = in.nextLine().toLowerCase();
        while (!(weaponChoice.equals("sword") || weaponChoice.equals("spear") || weaponChoice.equals("bow")));

        switch (weaponChoice) {
            case "sword" -> {
                he = heroTypes.get("warrior");
                he.equipWeapon(weapons.get("sword"));
                he.loadTexture("assets/heroes/warriorHero.txt");
            }
            case "spear" -> {
                he = heroTypes.get("lancer");
                he.equipWeapon(weapons.get("spear"));
                he.loadTexture("assets/heroes/lancerHero.txt");
            }
            case "bow" -> {
                he = heroTypes.get("archer");
                he.equipWeapon(weapons.get("bow"));
                he.loadTexture("assets/heroes/archerHero.txt");
            }
            default -> throw new IllegalStateException("Unexpected value: " + weaponChoice);
        }
    }


    public byte actions() throws FileNotFoundException {
        String action;

        byte statusCode = 0;    // 0: quit the game, 1: explore next chamber

        do {
            System.out.print("""
                    What would you like to do?
                    type 'e' to explore the next chamber/floor
                    type 'q' to quit the game
                    action:\s""");

            action = in.next().toLowerCase();

            if (action.equals("e")) statusCode = explore();
            else if (action.equals("q")) statusCode = quit(false);

        } while (!action.equals("e") && !action.equals("q"));

        return statusCode;
    }


    private byte explore() throws FileNotFoundException {
        currChamber++;

        if (currFloor == 0 || currChamber > 3) currFloor++;

        if (currChamber > 3)    currChamber = 1;

        if (currFloor == 1) System.out.println("Welcome to the first floor, chamber " + currChamber);
        else if (currFloor == nFloors) System.out.println("Welcome to the last floor, chamber " + currChamber);
        else System.out.printf("Welcome to floor %d, chamber %d%n", currFloor, currChamber);

        System.out.println();

        fight(currFloor, currChamber > 3 ? currChamber - 1 : currChamber);

        if (currFloor >= nFloors && currChamber == 3) return quit(true);

        return 1;
    }


    private void fight(final int floor, final int chamber) throws FileNotFoundException {
        Enemy currEnemy = dungeon[floor][chamber];
        int currDef = he.getDef();

        System.out.println("Your enemy is: " + currEnemy.getTag());
        currEnemy.loadTexture("assets/enemies/" + currEnemy.getTag() + ".txt");

        while (true) {
            System.out.print("Your current stats: ");
            he.printStats();
            System.out.print("Enemy current stats: ");
            currEnemy.printStats();

            System.out.print("\nYour turn! Attack (a) or stay in a defense position (d)? ");
            String move = in.next().toLowerCase();

            if (move.equals("a"))   he.hit(currEnemy);
            else if (move.equals("d"))  he.reinforces();

            if (currEnemy.getHp() <= 0) {
                System.out.println("You killed the " + currEnemy.getTag());
                he.setDef(currDef);
                break;
            }
            System.out.println("Enemy turn to attack!\n");
            currEnemy.hit(he);

            if (he.getHp() <= 0) {
                System.out.println("You got killed! :(\nYou can revive at the last chamber you've completed or you can restart a fresh adventure");
                quit(false);
                break;
            }
        }
    }


    private byte quit(boolean won) {
        if (won) System.out.println("Congratulations!!! Your cleared all the rooms in the dungeon!\nThanks for playing :)");
        return 0;
    }


    // Saves the current state of the game at the end of each fight
    public void autoSave() {

    }

}
