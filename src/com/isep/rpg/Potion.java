package com.isep.rpg;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public class Potion implements Consumable {
    private int manaPoints;

    public Potion(Builder b) {
        this.manaPoints += 5;
    }

    public void increaseEffectiveness() {
        this.manaPoints += 1;
    }

    @Override
    public int consume() {
        return this.manaPoints;
    }
}