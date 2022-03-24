package com.isep.rpg;

import java.util.List;
import java.util.Random;

public class Hunter extends Hero {
    public int arrows;
    public Hunter(){
        super(1000,1000,500,500,500);

    }

    public void attack() {
        Random random = new Random();
        this.heroDamage = random.nextInt(100;
        --this.arrows;
    }

    public void defend() {
        Random random = new Random();
        int degat = random.nextInt(100);
        int defence = random.nextInt(100);
        if (defence < 20 && degat > this.armor) {
            this.lifePoints += this.armor - degat;
        }

    }

    public void useConsumable(Consumable consumable) {
    }
}

