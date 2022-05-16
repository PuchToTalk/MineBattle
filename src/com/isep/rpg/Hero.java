package com.isep.rpg;

import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 17/05/2022)
 * Add le getArrows pour Hunters
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

    public abstract void attack(Enemy e);

    public abstract void defend(Enemy e);

    public abstract void useConsumable(Consumable con);

    public void addPotion(Potion p) {

        potions.add(p);
    }

    public void addFood(Food f) {

        lembas.add(f);
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


    public int getArrows() {
        return -1;
    }



}





