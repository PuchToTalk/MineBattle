package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public class Hunter extends Hero {
    private int arrows;

    public Hunter(Builder b) {
        this.name = b.name;
        this.arrows = b.arrows;
        this.armor = b.armor;
        this.lifePoints = b.lifePoints;
        this.weaponDamage = b.weaponDamage;
        this.lembas = b.lembas;
        this.potions = b.potions;
        loadFood();
    }

    public String increaseArrows() {
        this.arrows += 10;
        return "Arrows Increases Successfully";
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
            System.out.println("Arrows Finished");
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