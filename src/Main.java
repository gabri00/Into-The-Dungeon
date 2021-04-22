import Enemies.Enemy;
import Essentials.Hero;
import Weapons.Weapon;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private void initWorld() {
        System.out.print("""
                Welcome to the dungeon hero!
                Let's begin your adventure...
                """);
    }

     private void generateDungeon() {
        final int nFloors = 3;

        Random rand = new Random();
        final int nChambers = rand.nextInt(3);
    }

     private void beginExploration() throws InterruptedException, FileNotFoundException {
        Hero he = new Hero(150, 10, 10, 10);

        System.out.println("First of all, you should choose a weapon if you don't want to get killed!\nWeapon: ");
        Scanner in = new Scanner(System.in);
        String weaponChoice = in.nextLine();
        weaponChoice = weaponChoice.toLowerCase();

        switch (weaponChoice) {
            case "sword" -> {
                Weapon sword = new Weapon(0.0, 22.5, 0.0, 5.0);
                he.equipWeapon(sword);
                he.loadTexture("assets/heroes/swordHero.txt");
            }
            case "spear" -> {
                Weapon spear = new Weapon(10.0, 30.0, 0.0, 1.0);
                he.equipWeapon(spear);
                he.loadTexture("assets/heroes/spearHero.txt");
            }
            case "bow" -> {
                Weapon bow = new Weapon(20.0, 30.0, 0.0, 25.0);
                he.equipWeapon(bow);
                he.loadTexture("assets/heroes/archerHero.txt");
            }
            default -> throw new IllegalStateException("Unexpected value: " + weaponChoice);
        }

        System.out.println("Generating dungeon...");
        TimeUnit.SECONDS.sleep(1);
        generateDungeon();
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Main game = new Main();
        game.initWorld();
        game.beginExploration();
    }

}