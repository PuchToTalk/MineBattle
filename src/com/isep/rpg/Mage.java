package com.isep.rpg;


import java.util.Random;

public class Mage extends SpellCaster {
    public Mage() {
        this.lifePoints = 10;
        this.armor = 2;
        this.manaPoints = 20;
        this.manaConsomme = 3;
    }

    public void attack() {
        Random random = new Random();
        this.weaponDamage = random.nextInt(7) + 3;
        this.manaPoints -= this.manaConsomme;
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

