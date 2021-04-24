package Essentials;

import org.jetbrains.annotations.NotNull;

public class Enemy extends MainStats {

    public Enemy(String tag, double hp, double atk, double def, double crit) {
        super(tag, hp, atk, def, crit);
    }

    public void hit(@NotNull Hero he) {
        he.setHp(he.getHp() - this.getAtk() / he.getDef());
    }

}