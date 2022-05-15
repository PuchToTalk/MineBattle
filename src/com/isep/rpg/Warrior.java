package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 15/05/2022)
 */


public class Warrior extends Hero {
    public Warrior() {
        this.name;
        this.armor;
        this.lifePoints;
        this.weaponDamage;
        this.lembas;
        this.potions;
    }

    public int getManaCost() {
        return -1;
    }

    public int getManaPoints() {
        return -1;
    }

    @Override
    public void attack(Enemy e) {
        this.lifePoints -= e.getDamage();
    }

    @Override
    public void defend(Enemy e) {
        int extraEffect = this.armor - e.getDamage();
        if (extraEffect < 0) {
            this.lifePoints += extraEffect;
        }
    }
