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

    /** Même principe que pour Hero & Warrior on set up des commandes rapides via les Builders cf chap 7 **/

    public static class Builder {
        public int arrows;
        public String name;
        public int lifePoints;
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

        public void increaseArrow() {

            this.arrows += 5;
        }


        public Builder setName(String n) {
            this.name = n;
            return this;
        }

        public Builder setArrows(int a) {
            this.arrows = a;
            return this;
        }

        public Builder setWeaponDamage(int wd) {
            this.weaponDamage = wd;
            return this;
        }

        public Hunter build() {

            return new Hunter(this);
        }
    }

}
