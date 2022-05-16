package com.isep.rpg;

/**
 * @PS : Class ENEMY
 * Cette classe est la classe mère de deux autres classes (BasicEnemy & Boss)
 * Attributs sur LifePoints (points de vie) / name / damage
 *
 * @auteur(s)  (Paul)
 * @version (v.o1 - 16/05/2022)
 */


public abstract class Enemy


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
