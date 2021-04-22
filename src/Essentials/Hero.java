package Essentials;

import Enemies.Enemy;
import Weapons.Weapon;
import org.jetbrains.annotations.NotNull;


public class Hero extends MainStats {

    public Hero(double hp, double atk, double def, double crit) {
        super(hp, atk, def, crit);
    }

    public void hit(@NotNull Enemy en) {
        en.setHp(en.getHp() - this.getAtk() / en.getDef());
    }

    public void reinforces() {
        double def_percentage = this.getDef() * 0.1;    // Increment def by 10%
        this.setDef(this.getDef() + def_percentage);
    }

    public void equipWeapon(Weapon w) {
        if (w.getAtk() != 0)    this.setAtk(this.getAtk() + w.getAtk());
        if (w.getCrit() != 0)   this.setCrit(this.getCrit() + w.getCrit());
        if (w.getHp() != 0)     this.setHp(this.getHp() + w.getHp());
        if (w.getDef() != 0)    this.setDef(this.getDef() + w.getDef());
    }

    public void unequipWeapon(Weapon w) {
        if (w.getAtk() != 0)    this.setAtk(this.getAtk() - w.getAtk());
        if (w.getCrit() != 0)   this.setCrit(this.getCrit() - w.getCrit());
        if (w.getHp() != 0)     this.setHp(this.getHp() - w.getHp());
        if (w.getDef() != 0)    this.setDef(this.getDef() - w.getDef());
    }

}