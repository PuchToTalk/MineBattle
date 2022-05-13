package com.isep.rpg;

import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
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
        return "Armor Increase Successfully";
    }

    public String increaseWeaponDamage() {
        this.weaponDamage += 1;
        return "Weapon Damage Increases Successfully";
    }

    public String increasePotionAndFoodNumber() {
        loadFood();
        loadPotion();
        return "Food and Potion Increases Successfully";
    }

    public String increasePotionAndFoodEffectiveness() {
        for (Food lemba : lembas) {
            lemba.increaseEffectiveness();
        }
        for (Potion potion : potions) {
            potion.increaseEffectiveness();
        }

        return "Effectiveness of Food and Potion Increases Successfully";
    }

    public String increaseArrows() {
        return "Not Belong to Hunter Family";
    }

    public abstract int getManaPoints();

    public abstract int getManaCost();

    public String decraseManaCost() {
        return "No, Hero does not belong to the SpellCaster Family";
    }

    public abstract void attack(Enemy e);

    public abstract void defend(Enemy e);

    public abstract void useConsumable(Consumable con);

    @Override
    public String toString() {
        return "Hero{" +
                " armor=" + armor +
                ", name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", weaponDamage=" + weaponDamage +
                '}';
    }
}
