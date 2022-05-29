package com.isep.test;


/**
 * @auteur  (Paul)
 * @version (v.ok - 27/05/2022)
 */


import com.isep.rpg.Boss;
import com.isep.rpg.Enemy;
import com.isep.rpg.Food;
import com.isep.rpg.Warrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    Enemy e = new Boss.Builder(10).setName("Herobrine").setDamage(6).build();
    Warrior warrior = new Warrior.Builder(10).setArmor(3).setName("Warrior").setWeaponDamage(4).build();

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