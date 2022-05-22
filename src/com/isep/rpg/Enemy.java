package com.isep.rpg;
/**
 * @PS : Class ENEMY
 * Cette classe est la classe mère de deux autres classes (BasicEnemy & Boss)
 * Attributs sur LifePoints (points de vie) / name / damage
 *
 * @auteur(s)  (Paul)
 * @version (v.o2 - 22/05/2022)
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

    /**
     * Fonction qui provoque des dégâts au HERO
     * @param h = on cible le héro qui nous a attaqué
     * permet paramétrer l'affichage
     */


    public void damage(Hero h){

        lifePoints -= h.getWeaponDamage();
    }

    @Override
    public String toString(){

        return "[ "+this.name+", "+this.damage+", "+this.lifePoints+" ]";
    }
}
