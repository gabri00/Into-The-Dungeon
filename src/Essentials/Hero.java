package Essentials;

import org.jetbrains.annotations.NotNull;


public class Hero extends MainStats {

    public Weapon equippedWeapon;

    public Hero(String tag, double hp, double atk, double def, double crit) {
        super(tag, hp, atk, def, crit);
    }

    public void hit(@NotNull Enemy en) {
        en.setHp(en.getHp() - this.getAtk() / en.getDef());
    }

    public void reinforces() {
        double def_percentage = this.getDef() * 0.1;    // Increment def by 10%
        this.setDef(this.getDef() + def_percentage);
    }

    public void equipWeapon(Weapon w) {
        equippedWeapon = w;
        
        if (w.getAtk() != 0.0)    this.setAtk(this.getAtk() + w.getAtk());
        if (w.getCrit() != 0.0)   this.setCrit(this.getCrit() + w.getCrit());
        if (w.getHp() != 0.0)     this.setHp(this.getHp() + w.getHp());
        if (w.getDef() != 0.0)    this.setDef(this.getDef() + w.getDef());
    }

    public void unequipWeapon() {
        if (equippedWeapon.getAtk() != 0.0)    this.setAtk(this.getAtk() - equippedWeapon.getAtk());
        if (equippedWeapon.getCrit() != 0.0)   this.setCrit(this.getCrit() - equippedWeapon.getCrit());
        if (equippedWeapon.getHp() != 0.0)     this.setHp(this.getHp() - equippedWeapon.getHp());
        if (equippedWeapon.getDef() != 0.0)    this.setDef(this.getDef() - equippedWeapon.getDef());
    }

}