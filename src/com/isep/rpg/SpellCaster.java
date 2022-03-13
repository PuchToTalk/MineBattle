package com.isep.rpg;

public abstract class SpellCaster extends Hero {
    public int manaPoints;

    public SpellCaster() {
        super(6, 4, 5);
        this.manaPoints = 5;
    }
}
