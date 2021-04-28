package Essentials;

public class Enemy extends MainStats {

    private final int danger;       // 1: slimes and goblins, 2: ogre and demons, 3: archdemons and dragons
    private int expDrop;

    public Enemy(String tag, double hp, double atk, double def, double crit, int danger, int expDrop) {
        super(tag, hp, atk, def, crit);
        this.danger = danger;
        this.expDrop = expDrop;
    }

    public void hit(Hero he) {
        he.setHp(he.getHp() - (this.getAtk() - he.getDef()));
    }

}