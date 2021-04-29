package Essentials;

import java.util.Random;

public class Enemy extends MainStats {

    private final int danger;       // 1: slimes and goblins, 2: ogre and demons, 3: archdemons and dragons
    private final int expDrop;

    public Enemy(String tag, int hp, int atk, int def, int crit, int danger, int expDrop) {
        super(tag, hp, atk, def, crit);
        this.danger = danger;
        this.expDrop = expDrop;
    }

    public void hit(Hero he) {
        he.setHp(he.getHp() - (this.getAtk() - he.getDef()));
    }

    public void dropArtifact() {
        Random rand = new Random();

        int dropProb = 30 * danger;     // 30% * danger

        if (rand.nextInt(101) <= dropProb) {
            // Drop a random item based on the level of the enemy
        }
    }

}