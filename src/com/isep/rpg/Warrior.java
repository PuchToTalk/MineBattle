package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o2 - 17/05/2022)
 * acheminement des fonctions principaales du Warrior
 */

public class Warrior extends Hero {
    public Warrior(Builder b) {
        this.name = b.name;
        this.armor = b.armor;
        this.lifePoints = b.lifePoints;
        this.weaponDamage = b.weaponDamage;
        this.lembas = b.lembas;
        this.potions = b.potions;
        loadFood();
    }

    public int getManaCost() {
        return -1;
    }

    public int getManaPoints() {
        return -1;
    }


    public void attack(Enemy e) {
        this.lifePoints -= e.getDamage();
    }


    public void defend(Enemy e) {
        int extraEffect = this.armor - e.getDamage();
        if (extraEffect < 0) {
            this.lifePoints += extraEffect;
        }
    }

    public void useConsumable(Consumable con) {
        this.lifePoints += con.consume();
    }

    /** Builders qui permettent appel aux paramétrages rapides + commandes en appel simple via nom donné (cf chap 7) **/
    public static class Builder {
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

        public Builder setName(String n) {
            this.name = n;
            return this;
        }

        public Builder setWeaponDamage(int wd) {
            this.weaponDamage = wd;
            return this;
        }

        public Warrior build() {

            return new Warrior(this);
        }
    }
}