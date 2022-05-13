package com.isep.test;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


import game.Model.Boss;
import game.Model.Enemy;
import game.Model.Food;
import game.Model.Warrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    Enemy e = new Boss.Builder(10).setName("Minion").setDamage(6).build();
    Warrior warrior = new Warrior.Builder(10).setArmor(3).setName("Healer").setWeaponDamage(4).build();

    @Test
    void attack() {
        warrior.attack(e);
        assertEquals(4, warrior.getLifePoints());
    }

    @Test
    void defend() {
        warrior.defend(e);
        assertEquals(7, warrior.getLifePoints());
    }

    @Test
    void useConsumable() {
        warrior.useConsumable(new Food.Builder(2).build());
        assertEquals(warrior.getLifePoints(), 12);
    }
}