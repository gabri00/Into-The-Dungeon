package Essentials;

import static java.lang.Math.floor;

public class Hero extends MainStats {

    private Weapon equippedWeapon;
    private int level = 1;
    private int exp = 1;

    public Hero(String tag, double hp, double atk, double def, double crit) {
        super(tag, hp, atk, def, crit);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getExp() {
        return exp;
    }

    private void setEquippedWeapon(final Weapon w) {
        equippedWeapon = w;
    }

    private void setExp(final int exp) {
        this.exp = exp;
    }

    public void addExp(final int exp) {
        final int maxExp = level * 50;

        setExp(getExp() + exp);

        if (getExp() >= maxExp) {
            levelUp();

            setExp((getExp() > maxExp) ? (getExp() - maxExp) : 0);
        }
    }

    private void levelUp() {
        level++;
        setHp(floor(getHp() * 1.5));
        setAtk(getAtk() + 20);
        setDef(getDef() + 5);
        setCrit(getCrit());
    }

    public void hit(Enemy en) {
        en.setHp(en.getHp() - (this.getAtk() - en.getDef()));
    }

    public void reinforces() {
        this.setDef(this.getDef() + floor(this.getDef() * 0.05));  // Increment def by 5%
    }

    public void equipWeapon(final Weapon w) {
        setEquippedWeapon(w);

        if (w.getHp() != 0)     this.setHp(this.getHp() + w.getHp());
        if (w.getAtk() != 0)    this.setAtk(this.getAtk() + w.getAtk());
        if (w.getDef() != 0)    this.setDef(this.getDef() + w.getDef());
        if (w.getCrit() != 0)   this.setCrit(this.getCrit() + w.getCrit());
    }

    public void unequipWeapon() {
        if (equippedWeapon.getHp() != 0)     this.setHp(this.getHp() - equippedWeapon.getHp());
        if (equippedWeapon.getAtk() != 0)    this.setAtk(this.getAtk() - equippedWeapon.getAtk());
        if (equippedWeapon.getDef() != 0)    this.setDef(this.getDef() - equippedWeapon.getDef());
        if (equippedWeapon.getCrit() != 0)   this.setCrit(this.getCrit() - equippedWeapon.getCrit());

        setEquippedWeapon(null);
    }

}