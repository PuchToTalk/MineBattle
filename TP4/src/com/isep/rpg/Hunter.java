package com.isep.rpg;


import java.util.Random;

public class Hunter extends Hero {
    public int arrows;

    public Hunter() {
        this.lifePoints = 15;
        this.armor = 5;
        this.arrows = 10;
    }

    public void attack() {
        Random random = new Random();
        this.weaponDamage = random.nextInt(10);
        --this.arrows;
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
