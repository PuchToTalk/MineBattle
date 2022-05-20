package com.isep.rpg;
import java.util.Random;
public class BasicEnemy extends Enemy
{
    private BasicEnemy(Builder b){
        this.lifePoints = b.lifePoints;
        this.name = b.name;
        this.damage = b.damage;
    }


    /**
     * @PS : Class BasicEnemy
     * Cette classe dispose les attributs qu'elle a obtenu par héritage de sa classe mère (Enemy)
     * idem attributs que Boss aussi
     *
     * @auteur(s)  (Paul)
     * @version (v.o2 - 20/05/2022)
     */


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

