package com.isep.rpg;

import java.util.Random;

public class Healer extends SpellCaster{
}

    public Healer() {
        super(1000, 1000, 500, 100, 50);

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
