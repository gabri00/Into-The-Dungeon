import Enemies.Enemy;
import Essentials.Hero;

public class Main {

    public static void main(String[] args) {
        Hero h = new Hero(10, 10, 10, 10);
        Enemy e = new Enemy(4, 4, 4, 4);

        System.out.printf("Essentials.Hero: %s%n", h.getDef());
    }

}