import Essentials.Enemy;
import Essentials.Hero;
import Essentials.Weapon;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private Map<Integer, Enemy> mapEnemies() {
        Map<Integer, Enemy> enemies = new HashMap<>();

        enemies.put(0, new Enemy("slime", 20.0, 10.0, 40.0, 0.0));      // 0 : slime
        enemies.put(1, new Enemy("demon", 250.0, 60.0, 40.0, 20.0));    // 1 : demon
        enemies.put(2, new Enemy("dragon", 2000.0, 90.0, 50.0, 10.0));  // 2 : dragon

        return enemies;
    }

    private void initWorld() {
        System.out.print("""
                Welcome to the dungeon hero!
                Let's begin your adventure...
                """);

        System.out.print("Increase the console size for a better gaming experience!");
    }

     private void generateDungeon(Map<Integer, Enemy> enemies) {
        Random rand = new Random();

        final int nFloors = 3;
        final int nChambers = 2;
        Enemy[][] dungeon = new Enemy[nChambers][nFloors];

        for (int i = 0; i < nChambers; i++) {
            for (int j = 0; j < nFloors; j++)
                dungeon[i][j] = enemies.get(rand.nextInt(3));
        }

        for (int i = 0; i < nChambers; i++) {
            for (int j = 0; j < nFloors; j++) {
                var temp = dungeon[i][j];
                System.out.println("Chamber " + (i+1) + ", floor " + (j+1) + ", Enemy: " + temp.getTag() + ", stats: " + temp.getHp() + " " + temp.getAtk() + " " + temp.getDef() + " " + temp.getCrit());
            }
        }
    }

     private void beginExploration() throws InterruptedException, FileNotFoundException {
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

        System.out.println("Generating dungeon...");
        TimeUnit.SECONDS.sleep((long)0.5);

        var enemies = mapEnemies();
        generateDungeon(enemies);
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Main game = new Main();
        game.initWorld();
        game.beginExploration();
    }

}