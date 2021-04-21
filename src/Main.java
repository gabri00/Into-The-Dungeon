public class Main {

    public static void main(String[] args) {
        Hero h = new Hero(10, 10, 10, 10);
        Enemy e = new Enemy(4, 4, 4, 4);

        System.out.printf("Hero: %s%n", h.getDef());
    }

}