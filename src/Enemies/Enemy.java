package Enemies;

import Essentials.MainStats;
import Essentials.Hero;
import org.jetbrains.annotations.NotNull;

public class Enemy extends MainStats {

    public Enemy(double hp, double atk, double def, double crit) {
        super(hp, atk, def, crit);
    }

    public void hit(@NotNull Hero he) {
        he.setHp(he.getHp() - this.getAtk() / he.getDef());
    }

}