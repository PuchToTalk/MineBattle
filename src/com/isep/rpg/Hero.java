package com.isep.rpg;


import java.util.ArrayList;

public abstract class Hero {
    protected String name;
    public int lifePoints;
    protected int armor;
    public int weaponDamage;
    protected ArrayList<Potion> potions;
    protected ArrayList<Food> lembas;

    public Hero() {
    }

    public abstract void attack();

    public abstract void defend();

    public abstract void useConsumable(Consumable var1);

    public void givePotion() {
        Potion potion = new Potion();
        this.potions.add(potion);
    }

    public void giveFood() {
        Food food = new Food();
        this.lembas.add(food);
    }
}

