package Classes;

import java.util.Random;

public class Enemy extends MainStats {

    private final int danger;       // 1: slimes and goblins, 2: ogre and demons, 3: archdemons and dragons
    private final int expDrop;

    public Enemy(final String tag, int hp, int atk, int def, int crit, final int danger, final int expDrop) {
        super(tag, hp, atk, def, crit);
        this.danger = danger;
        this.expDrop = expDrop;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public void hit(Hero he) {
        final int dmg = this.getAtk() - he.getDef();
        if (dmg > 0) he.setHp(he.getHp() - dmg);
    }

    public void dropArtifact() {
        Random rand = new Random();

        int dropProb = 30 * danger;     // 30% * danger

        if (rand.nextInt(101) <= dropProb) {
            // Drop a random item based on the level of the enemy
        }
    }

}