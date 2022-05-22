package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;


/**
 * @auteur  (Paul)
 * @version (v.o2 - 22/05/2022)
 * Attaques de base
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

    public void useConsumable(Consumable con) {
        if (con instanceof Potion) {
            this.manaPoints += con.consume();
        } else if (con instanceof Food) {
            this.lifePoints += con.consume();
        }
    }


    /**
     * @auteur  (Paul)
     * @version (v.o3 - 23/05/2022)

    Optimisation des Constructeurs : Armure + Dégâts + Nom + [...]

     *    [...] + Particularité des SpellCasters (fonctions setup Mana & Coût des sorts en Mana)
     *
     **/



    public static class Builder {
        public int manaCost;
        public int manaPoints;
        public int lifePoints;
        public String name;
        public int armor;
        public int weaponDamage;
        public List<Potion> potions;
        public List<Food> lembas;

        public Builder(int lifePoints) {
            this.lifePoints = lifePoints;
            potions = new ArrayList<>();
            lembas = new ArrayList<>();
        }

        public Builder setArmor(int armor) {
            this.armor = armor;
            return this;
        }


        public Builder setWeaponDamage(int wd) {
            this.weaponDamage = wd;
            return this;
        }


        public Builder setName(String n) {
            this.name = n;
            return this;
        }


        /** Propre aux SpellCasters (setup ManaPoints, Coût Mana) **/

        public Builder setManaPoints(int mp) {
            this.manaPoints = mp;
            return this;
        }


        public Builder setManaCost(int mc) {
            this.manaCost = mc;
            return this;
        }


        public Healer build() {
            return new Healer(this);
        }
    }
}