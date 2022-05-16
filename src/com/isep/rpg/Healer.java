package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 16/05/2022)
 */


public class Healer extends SpellCaster {
    public Healer(Builder b) {
        this.name = b.name;
        this.manaCost = b.manaCost;
        this.manaPoints = b.manaPoints;
        this.armor = b.armor;
        this.lifePoints = b.lifePoints;
        this.weaponDamage = b.weaponDamage;
        this.lembas = b.lembas;
        this.potions = b.potions;
        loadFood();
        loadPotion();
    }


    public void attack(Enemy e) {
        this.lifePoints -= e.getDamage();
        if (manaPoints > 0)
            this.manaPoints -= this.manaCost;
    }


    public void defend(Enemy e) {
        int extraEffect = this.armor - e.getDamage();
        if (extraEffect < 0) {
            this.lifePoints += extraEffect;
        }
    }
