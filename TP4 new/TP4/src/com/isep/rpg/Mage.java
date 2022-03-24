package com.isep.rpg;

import java.util.Random;

public class Mage extends SpellCaster{

    public Mage() {
        super(100, 100, 100, 100, 5);
    }

    public void attack() {
        Random random = new Random();
        this.heroDamage = random.nextInt(100) + 1;
        this.manaPoints -= this.manaConsomme;
    }

    public void defend() {
        Random random = new Random();
        int degat = random.nextInt(100);
        int defence = random.nextInt(100);
        if (defence < 5 && degat > this.armor) {
            this.lifePoints += this.armor - degat;
        }

    }

    public void useConsumable(Consumable consumable) {
    }
}
