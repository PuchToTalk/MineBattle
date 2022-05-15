package com.isep.rpg;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


import java.util.ArrayList;
import java.util.List;

public class Mage extends SpellCaster {
    public Mage(Builder b) {
        this.manaCost = b.manaCost;
        this.name = b.name;
        this.manaPoints = b.manaPoints;
        this.armor = b.armor;
        this.lifePoints = b.lifePoints;
        this.weaponDamage = b.weaponDamage;
        this.lembas = b.lembas;
        this.potions = b.potions;
        loadFood();
        loadPotion();
    }


    @Override
    public void attack(Enemy e) {
        this.lifePoints -= e.getDamage();
        this.manaPoints -= this.manaCost;
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
        System.out.println(con instanceof Potion);
        if (con instanceof Potion) {
            this.manaPoints += con.consume();
        } else if (con instanceof Food) {
            this.lifePoints += con.consume();
        }
    }
}