package com.isep.rpg;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public abstract class Enemy
{
    //lifePoints Of Enemy Attribute
    protected int lifePoints;
    protected String name;
    protected int damage;

    public int getLifePoints(){
        return this.lifePoints;
    }

    public int getDamage(){
        return this.damage;
    }

    public String getName(){
        return this.name;
    }


    /** Les méthodes */


    public void damage(Hero h){
        lifePoints -= h.getWeaponDamage();
    }

    @Override
    public String toString(){
        return "[ "+this.name+", "+this.damage+", "+this.lifePoints+" ]";
    }
}
