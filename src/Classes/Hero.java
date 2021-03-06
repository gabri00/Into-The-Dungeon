package Classes;

public class Hero extends MainStats {

    private Weapon equippedWeapon;
    private int level = 1;
    private int exp = 1;

    public Hero(final String tag, int hp, int atk, int def, int crit) {
        super(tag, hp, atk, def, crit);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public int getLevel() {
        return level;
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
        setHp((int) (getHp() * 1.5));
        setAtk(getAtk() + 20);
        setDef(getDef() + 5);
        setCrit(getCrit());

        System.out.println("You upgraded to " + level);
    }

    public void hit(Enemy en) {
        final int dmg = this.getAtk() - en.getDef();
        if (dmg > 0) en.setHp(en.getHp() - dmg);
    }

    public void reinforces() {
        this.setDef(this.getDef() + 3);
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