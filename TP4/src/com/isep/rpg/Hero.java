package com.isep.rpg;

import java.util.*;


public abstract class Hero extends Game{
    public int lifePoints;
    public int armor;
    public int heroDamage;

    public boolean HAlive;
    public List<Enemy> EAlive;



    public Hero(int lifePoints, int armor, int heroDamage){
        this.lifePoints = lifePoints;
        this.armor = armor;
        this.heroDamage = heroDamage;

    }


    public abstract void heroAttack(Hero Hero, Enemy Enemy) {
        private int lifeEnemies;
        private int getDamage;
        lifeEnemies = Enemy.lifePoints;
        getDamage = heroDamage;
        Enemy.lifePoints = (lifeEnemies - getDamage);
        if Enemy.lifePoints < 0 {
            System.out.println(" L'ennemie a été battu ! ");
        }
        else{
            System.out.println(" L'ennemie est toujours vivant ");
            }

    }

    public abstract void heroDefend(Hero Hero, Enemy Enemy){

    }




}
