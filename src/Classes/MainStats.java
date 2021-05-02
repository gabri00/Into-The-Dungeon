package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Contains the main functionalities and the main attributes for all the game classes

public class MainStats implements java.io.Serializable {
    private final String tag;
    private int hp, atk, def, crit;

    public MainStats(String tag, int hp, int atk, int def, int crit) {
        this.tag = tag;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.crit = crit;
    }

    public String getTag() {
        return tag;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getCrit() {
        return crit;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public void loadTexture(String textureFile) throws FileNotFoundException {
        File texture = new File(textureFile);
        Scanner reader = new Scanner(texture);

        if (texture.canRead()) {
            while (reader.hasNextLine())
                System.out.println(reader.nextLine());
            reader.close();
        }
    }

    public void printStats() {
        System.out.println("HP: " + getHp() + ", ATK: " + getAtk() + ", DEF: " + getDef() + ", CR: " + getCrit());
    }

}