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

    public void defend() {
        double def_percentage = this.getDef() * 0.1;    // Increment def by 10%
        this.setDef(this.getDef() + def_percentage);
    }

    public void equipWeapon(Weapon w) {

    }

    public void unequipWeapon(Weapon w) {

    }

}