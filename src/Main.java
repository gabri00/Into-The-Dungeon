import Essentials.Enemy;
import Essentials.Hero;
import Essentials.Weapon;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

//    private static final int STATS = 4;

    private Map<Integer, Enemy> generateEnemies() {
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
    }

     private void generateDungeon(Map<Integer, Enemy> enemies) {
        Random rand = new Random();

        final int nFloors = 3;
        final int nChambers = rand.nextInt(3) + 1;
        Enemy[][] dungeon = new Enemy[nChambers][nFloors];

        for (int i = 0; i < nChambers; i++) {
            for (int j = 0; j < nFloors; j++)
                dungeon[i][j] = enemies.get(rand.nextInt(3));
        }

        for (int i = 0; i < nChambers; i++) {
            for (int j = 0; j < nFloors; j++) {
                var temp = dungeon[i][j];
                System.out.println("Chamber " + (i+1) + ", floor " + (j+1) + ", stats: " + temp.getHp() + " " + temp.getAtk() + " " + temp.getDef() + " " + temp.getCrit());
            }
        }
    }

     private void beginExploration() throws InterruptedException, FileNotFoundException {
        Hero he = new Hero(150, 10, 10, 10);

        System.out.print("First of all, you should choose a weapon if you don't want to get killed!\nWeapon: ");
        Scanner in = new Scanner(System.in);
        String weaponChoice = in.nextLine();
        weaponChoice = weaponChoice.toLowerCase();

        switch (weaponChoice) {
            case "sword" -> {
                Weapon sword = new Weapon(0.0, 22.5, 0.0, 5.0);
                he.equipWeapon(sword);
                he.loadTexture("assets/heroes/swordHero.txt");
                he.equippedWeapon = "sword";
            }
            case "spear" -> {
                Weapon spear = new Weapon(10.0, 30.0, 0.0, 1.0);
                he.equipWeapon(spear);
                he.loadTexture("assets/heroes/spearHero.txt");
                he.equippedWeapon = "spear";
            }
            case "bow" -> {
                Weapon bow = new Weapon(20.0, 30.0, 0.0, 25.0);
                he.equipWeapon(bow);
                he.loadTexture("assets/heroes/archerHero.txt");
                he.equippedWeapon = "bow";
            }
            default -> throw new IllegalStateException("Unexpected value: " + weaponChoice);
        }

        System.out.println("Generating dungeon...");
        TimeUnit.SECONDS.sleep(2);

        var enemies = generateEnemies();
        generateDungeon(enemies);
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Main game = new Main();
        game.initWorld();
        game.beginExploration();
    }

}