package com.isep.rpg;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 17/05/2022)
 * Ajout builders comme cf Hunter & Warriors
 * ajout gain en mp grâce potions mp
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



    public void attack(Enemy e) {
        this.lifePoints -= e.getDamage();
        this.manaPoints -= this.manaCost;
    }


    public void defend(Enemy e) {
        int extraEffect = this.armor - e.getDamage();
        if (extraEffect < 0) {
            this.lifePoints += extraEffect;
        }
    }


    public void useConsumable(Consumable con) {
        System.out.println(con instanceof Potion);
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
        public int armor;
        public String name;
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

        /** Fonctions Propres aux SpellCasters (fonctions setup Mana & Coût des sorts en Mana) **/

        public Builder setManaPoints(int mp) {
            this.manaPoints = mp;
            return this;
        }

        public Builder setManaCost(int mc) {
            this.manaCost = mc;
            return this;
        }

        public Mage build() {

            return new Mage(this);
        }
    }
}
