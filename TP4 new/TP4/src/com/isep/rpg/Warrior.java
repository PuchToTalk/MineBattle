package com.isep.rpg;


import java.util.Random;

public class Warrior extends Hero {
    public Warrior() {
        this.lifePoints = 25;
        this.armor = 5;
    }

    public void attack() {
        Random random = new Random();
        this.heroDamage = random.nextInt(100) + 1;
    }

    public void defend() {
        Random random = new Random();
        int degat = random.nextInt(100);
        int defence = random.nextInt(100);
        if (defence < 50 && degat > this.armor) {
            this.lifePoints += this.armor - degat;
        }

    }

    public void useConsumable(Consumable consumable) {
    }
}

