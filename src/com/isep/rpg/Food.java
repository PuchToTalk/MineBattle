package com.isep.rpg;

/**
 * @auteur  (Paul)
 * @version (v.o3 - 22/05/2022)
 * Ajoute les effets qu'on obtient après choix récompenses : increaseEffectiveness
 * Ajout builders pour faciliter constructeur
 */


public class Food implements Consumable
{
    private int lifePoints;

    private Food(Builder b){

        this.lifePoints = b.lifePoints;
    }

    public void increaseEffectiveness(){

        this.lifePoints += 1;
    }

    @Override
    public int consume(){

        return this.lifePoints;
    }

    public static class Builder{
        public int lifePoints;

        public Builder(int lifePoints){

            this.lifePoints = lifePoints;
        }

        public Food build(){
            return new Food(this);
        }
    }
}
