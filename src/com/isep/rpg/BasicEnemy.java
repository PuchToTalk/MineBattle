package com.isep.rpg;
import java.util.Random;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public class BasicEnemy extends Enemy
{
    private BasicEnemy(Builder b){
        this.lifePoints = b.lifePoints;
        this.name = b.name;
        this.damage = b.damage;
    }






    public static class Builder{

        public int lifePoints;
        public String name;
        public int damage;

        public Builder(int lp){
            this.lifePoints = lp;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setDamage(int damage){
            this.damage = damage;
            return this;
        }

        public BasicEnemy build(){
            return new BasicEnemy(this);
        }
    }
}

