package Essentials;

import org.jetbrains.annotations.NotNull;

public class Enemy extends MainStats {

    private final int danger;       // 1: slimes and goblins, 2: ogre and demons, 3: archdemons and dragons

    public Enemy(String tag, double hp, double atk, double def, double crit, int danger) {
        super(tag, hp, atk, def, crit);
        this.danger = danger;
    }

    public void hit(@NotNull Hero he) {
        he.setHp(he.getHp() - this.getAtk() / he.getDef());
    }

}