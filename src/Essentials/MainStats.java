package Essentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainStats {
    private double hp, atk, def, crit;

    public MainStats(double hp, double atk, double def, double crit) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.crit = crit;
    }

    public double getHp() {
        return hp;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }

    public double getCrit() {
        return crit;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public void setCrit(double crit) {
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

}