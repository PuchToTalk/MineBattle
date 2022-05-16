package com.isep.rpg;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 16/05/2022)
 */


public abstract class SpellCaster extends Hero {
    protected int manaPoints;
    protected int manaCost;


    public int getManaCost() {

        return this.manaCost;
    }

    public String decraseManaCost() {
        if (manaCost > 1) {
            this.manaCost -= 1;
            return "Diminution du cout en Mana";
        }
        return "Cout en Mana ne peut pas être négatif";
    }

    public int getManaPoints() {

        return this.manaPoints;
    }
}
