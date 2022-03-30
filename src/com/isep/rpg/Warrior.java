package com.isep.rpg;


import java.util.Random;

public class Warrior extends Hero {
    public Warrior() {
        this.lifePoints = 25;
        this.armor = 5;
    }

    public void attack() {
        Random random = new Random();
        this.weaponDamage = random.nextInt(10) + 1;
    }

    public void defend() {
        Random random = new Random();
        int degat = random.nextInt(10);
        int defence = random.nextInt(10);
        if (defence < 5 && degat > this.armor) {
            this.lifePoints += this.armor - degat;
        }

    }

    public void useConsumable(Consumable consumable) {
    }
}