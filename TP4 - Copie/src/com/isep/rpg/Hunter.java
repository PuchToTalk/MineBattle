package com.isep.rpg;


import java.util.ArrayList;
import java.util.List;


/**
 * @auteur  (Paul)
 * @version (v.o2 - 22/05/2022)
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



   /** Respecter la condition "diminuer le nbre de flèche lors attaque"
    * Puis incapacité d'attaquer & renvoie msg erreur si plus de flèches **/

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



    /** Fonctions propres à la classe "Hunter" lorsque choix de recompenses correspondant **/

    public String increaseArrows() {
        this.arrows += 10;
        return "Augmentation Fleches"; // renvoie msg pour montrer que bien réalisé, pour savoir en cas d'erreur
    }


    public int getArrows() {

        return this.arrows; // infos sur qtité flèches si existe
    }


    /**
     * @auteur  (Paul)
     * @version (v.o3 - 23/05/2022)
     * Définir comme sur Warrior par défaut, mettre à valeur négatif (-1) pour la barre en Mana
     * Pour éviter qu'il rentre dans les conditions fixées lorsque x > 1
     */

    public int getManaCost() {
        return -1;
    }

    public int getManaPoints() {
        return -1;
    }


    /** Même principe que pour Hero & Warrior on set up des commandes rapides via les Builders cf chap 7 **/

    /**  Optimisation des Constructeurs : Armure + Dégâts + Nom + stats de départ
     **/


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



        public Builder setName(String n) {
            this.name = n;
            return this;
        }


        public Builder setWeaponDamage(int wd) {
            this.weaponDamage = wd;
            return this;
        }


        /** Propre à la classe
         * & Dépend avec choix des récompenses **/

        public Builder setArrows(int a) {
            this.arrows = a;
            return this;
        }

        public void increaseArrow() {

            this.arrows += 5;
        }

        public Hunter build() {

            return new Hunter(this);
        }
    }

}
