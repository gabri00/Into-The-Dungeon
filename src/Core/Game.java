package Core;

import Classes.Artifact;
import Classes.Enemy;
import Classes.Hero;
import Classes.Weapon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game implements java.io.Serializable {

    private transient Random rand = new Random();
    private transient Scanner in = new Scanner(System.in);

    private final transient int nFloors = 10, nChambers = 3;

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

        do weaponChoice = in.nextLine().trim().toLowerCase();
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

            Scanner in2 = new Scanner(System.in);
            action = in2.next().toLowerCase();

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
        else System.out.printf("\nWelcome to floor %d, chamber %d%n", currFloor, currChamber);

        fight(currFloor, currChamber >= 3 ? currChamber - 1 : currChamber);

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
            Scanner in2 = new Scanner(System.in);
            String move = in2.next().trim().toLowerCase();

            if (move.equals("a"))   he.hit(currEnemy);
            else if (move.equals("d"))  he.reinforces();
            else System.out.println("Command incorrect");

            if (currEnemy.getHp() <= 0) {
                System.out.println("You killed the " + currEnemy.getTag());
                he.setDef(currDef);
                he.addExp(currEnemy.getExpDrop());
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
    public void autoSave(Game game) {
        try {
            FileOutputStream fos = new FileOutputStream("log.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.println("Game saved!\n");
        } catch (IOException e) {
            System.out.println("Serialization error!\n" + e.getClass() + ": " + e.getMessage());
        }
    }


    // Loads the last game state
    public Game loadGame() {
        Game game = null;

        try {
            FileInputStream fis = new FileInputStream("log.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
            System.out.println("Game loaded");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Serialization error!\n" + e.getClass() + ": " + e.getMessage());
        }

        return game;
    }

}
