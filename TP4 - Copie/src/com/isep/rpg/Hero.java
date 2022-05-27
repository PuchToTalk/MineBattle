package com.isep.rpg;

import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o2 - 20/05/2022)
 * Différencier les différentes parties du scénario
 */



public abstract class Hero {
    protected int armor;
    protected String name;
    protected int lifePoints;
    protected int weaponDamage;
    protected List<Food> lembas;
    protected List<Potion> potions;

    public int getArmor() {
        return this.armor;
    }

    public String getName() {
        return this.name;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    public List<Food> getLembas() {
        return this.lembas;
    }

    public List<Potion> getPotions() {
        return this.potions;
    }


    protected void loadFood() {
        lembas.add(new Food.Builder(2).build());
        lembas.add(new Food.Builder(2).build());
        lembas.add(new Food.Builder(2).build());
    }

    protected void loadPotion() {
        potions.add(new Potion.Builder(3).build());
        potions.add(new Potion.Builder(2).build());
    }



    /**
     * @auteur  (Paul)
     * @version (v.o3 - 22/05/2022)
     * Scénario Récompenses
     */


    public int getArrows() {
        return -1;
    }

    public void addPotion(Potion p) {
        potions.add(p);
    }

    public void addFood(Food f) {
        lembas.add(f);
    }

    public String increaseArmor() {
        this.armor += 1;
        return "Augmentation de l'Armure";
    }

    public String increaseWeaponDamage() {
        this.weaponDamage += 1;
        return "Augmentation du Degat";
    }

    public String increasePotionAndFoodNumber() {
        loadFood();
        loadPotion();
        return "Augmentation du Nombre de Food & Potion";
    }

    public String increasePotionAndFoodEffectiveness() {
        for (Food lemba : lembas) {
            lemba.increaseEffectiveness();
        }
        for (Potion potion : potions) {
            potion.increaseEffectiveness();
        }

        return "Efficacite augmenté";
    }

    public String increaseArrows() {

        return "Capacité propre au Hunter";
    }

    public String decraseManaCost() {

        return "Capacité propre aux SpellCaster";
    }





    /**
     * @auteur  (Paul)
     * @version (v.o3 - 22/05/2022)
     * Informations sur les persos & commandes de base
     * Affichage du Héro
     */

    //commandes de base
    public abstract void attack(Enemy e);

    public abstract void defend(Enemy e);

    public abstract void useConsumable(Consumable con);




    // infos sur stats (si classe Spellcaster et Mana non négatif)
    public abstract int getManaPoints();

    public abstract int getManaCost();



    @Override
    public String toString() {
        return "Hero{" +
                " Armure=" + armor +
                ", Nom='" + name + '\'' +
                ", HP=" + lifePoints +
                ", Degats=" + weaponDamage +
                '}';
    }
}






