package com.isep.rpg;

package game.Model;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public class Boss extends Enemy
{
    public Boss(Builder b){
        this.lifePoints = b.lifePoints;
        this.name = b.name;
        this.damage = b.damage;
    }



    public static class Builder{

        public int lifePoints;
        public int damage;
        public String name;

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

        public Boss build(){
            return new Boss(this);
        }
    }
}
