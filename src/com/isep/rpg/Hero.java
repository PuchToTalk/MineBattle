package com.isep.rpg;

import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public abstract class Hero {
    protected int armor;
    protected String name;
    protected int lifePoints;
    protected int weaponDamage;
    protected List<Food> lembas;
    protected List<Potion> potions;

    public int getArmor() {
        return this.armor;
    }

    public String getName() {
        return this.name;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    public List<Food> getLembas() {
        return this.lembas;
    }

    public List<Potion> getPotions() {
        return this.potions;
    }


    @Override
    public String toString() {
        return "Hero{" +
                " Armure=" + armor +
                ", Nom='" + name + '\'' +
                ", HP=" + lifePoints +
                ", Degats=" + weaponDamage +
                '}';
    }
}




