public class Character {
    private double hp, atk, def, crit;

    public Character(double hp, double atk, double def, double crit) {
        double this.hp = hp;
        double this.atk = atk;
        double this.def = def;
        double this.crit = crit;
    }

    public void getHp() {
        return hp;
    }

    public void getAtk() {
        return atk;
    }

    public void getDef() {
        return def;
    }

    public void getCrit() {
        return crit;
    }

    public double setHp(double hp) {
        this.hp = hp;
    }

    public double setAtk(double atk) {
        this.atk = atk;
    }

    public double setDef(double def) {
        this.def = def;
    }

    public double setCrit(double crit) {
        this.crit = crit;
    }

}