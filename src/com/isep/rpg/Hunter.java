package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 17/05/2022)
 * Augmentation flèches
 * Plus de flèches en stock
 * obtenir flèches lors de la récompense
 */


public class Hunter extends Hero {
    private int arrows;

    public Hunter() {
        this.name;
        this.arrows;
        this.armor;
        this.lifePoints;
        this.weaponDamage;
        this.lembas;
        this.potions;

    }

    public String increaseArrows() {
        this.arrows += 10;
        return "Augmentation Fleches";
    }

    public int getManaCost() {

        return -1;
    }


    public int getManaPoints() {

        return -1;
    }

    @Override
    public void attack(Enemy e) {
        if (arrows > 0) {
            this.lifePoints -= e.getDamage();
            this.arrows--;
        } else {
            System.out.println("Plus de fleches");
        }
    }

    @Override
    public void defend(Enemy e) {
        int extraEffect = this.armor - e.getDamage();
        if (extraEffect < 0) {
            this.lifePoints += extraEffect;
        }
    }

    @Override
    public void useConsumable(Consumable con) {

        this.lifePoints += con.consume();
    }

    public int getArrows() {

        return this.arrows;
    }





}