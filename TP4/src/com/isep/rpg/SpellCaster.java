package com.isep.rpg;

/**
 * @auteur  (Paul)
 * @version (v.o2 - 22/05/2022)
 * Fonction renvoie le coût actuel
 * Fonction renvoie les points Mana restants
 */


public abstract class SpellCaster extends Hero {
    protected int manaPoints;
    protected int manaCost;


    @Override
    public int getManaCost() {

        return this.manaCost;
    }

    @Override
    public int getManaPoints() {

        return this.manaPoints;
    }


    /**
     * @auteur  (Paul)
     * @version (v.o3 - 23/05/2022)

     * Fonction pour dminution du coût en Mana //
     */



    @Override
    public String decraseManaCost() {
        if (manaCost > 1) {
            this.manaCost -= 1;
            return "Diminution du cout en Mana";
        }
        return "Cout en Mana ne peut pas être négatif";
    }


}