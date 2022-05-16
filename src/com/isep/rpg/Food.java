package com.isep.rpg;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 16/05/2022)
 */


public class Food implements Consumable
{
    private int lifePoints;

    private Food(){
        this.lifePoints += 5
    }


    @Override
    public int consume(){
        return this.lifePoints;
    }

}
