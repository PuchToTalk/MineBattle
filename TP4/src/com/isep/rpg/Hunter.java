package com.isep.rpg;


import java.util.Random;

public class Hunter extends Hero {
    protected int arrows;

    public Hunter() {
        this.lifePoints = 15;
        this.armor = 3;
        this.arrows = 30;
    }

    public void attack() {
        Random random = new Random();
        this.weaponDamage = random.nextInt(7);
        --this.arrows;
    }

    public void defend() {
        Random random = new Random();
        int degat = random.nextInt(7);
        int defence = random.nextInt(7);
        if (defence < 5 && degat > this.armor) {
            this.lifePoints += this.armor - degat;
        }

    }

    public void useConsumable(Consumable consumable) {
    }
}
