package com.isep.rpg;

public abstract class Enemy {
    public int lifePoints;
    private String name;

    public Enemy(int lifePoints, String name){
        this.lifePoints = lifePoints;
        this.name = name;
    }

    public void showEnemy(){
        System.out.println(getName() + " a : " + getLifePoints() + " de point(s) de vie.");
    }

    public int getLifePoints(){
        return lifePoints;
    }
    public String getName(){ return name;}

}
