package com.isep.rpg;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public abstract class SpellCaster extends Hero {
    protected int manaPoints;
    protected int manaCost;

    @Override
    public int getManaCost() {
        return this.manaCost;
    }

    @Override
    public String decraseManaCost() {
        if (manaCost > 1) {
            this.manaCost -= 1;
            return "ManaCost Decrease Successfully";
        }
        return "ManaCost cannot be less than 1";
    }

    @Override
    public int getManaPoints() {
        return this.manaPoints;
    }

}
