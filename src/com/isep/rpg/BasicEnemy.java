package com.isep.rpg;
import java.util.Random;
/**
 * @auteur  (Paul)
 * @version (v.o1 - 16/05/2022)
 */



public class BasicEnemy extends Enemy {
    public BasicEnemy() {
        this.lifePoints = 10;
        this.name = "Minion";
        this.weaponDamage = 2;
    }
}
