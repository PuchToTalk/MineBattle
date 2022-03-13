package com.isep.rpg;

import java.util.List;

public abstract class Enemy {
    public int lifePoints;
    public int EnemyDamage;
    private String name;

    public Enemy(int lifePoints, int EnemyDamage, String name){
        this.lifePoints = lifePoints;
        this.EnemyDamage = EnemyDamage;
        this.name = name;
    }


    public abstract void enemyAttack(Hero Hero, Enemy Enemy) {
        private int lifeMainHero;
        private int getDamage;
        lifeMainHero = Hero.lifePoints;
        getDamage = EnemyDamage;
        Hero.lifePoints = (lifeMainHero - getDamage);
        if Hero.lifePoints < 0 {
            System.out.println(" Le héro a été battu ! ");
        }
        else{
            System.out.println(" Le héro est toujours vivant ");
        }
    }

    public abstract void enemyDefend(Hero Hero, Enemy Enemy) {

    }





    // Tests unitaires de fonctions pour un personnage par exemple
    public void showEnemy(){
        System.out.println(getName() + " a : " + getLifePoints() + " de point(s) de vie.");
    }

    public int getLifePoints(){
        return lifePoints;
    }
    public String getName(){ return name;}

}
