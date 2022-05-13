package com.isep.test;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */

import game.Model.BasicEnemy;
import game.Model.Enemy;
import game.Model.Healer;
import game.Model.Potion;

import static org.junit.jupiter.api.Assertions.*;

class HealerTest {
    Enemy e = new BasicEnemy.Builder(10).setName("Minion").setDamage(2).build();
    Healer healer = new Healer.Builder(10).setArmor(3).setManaCost(2).setManaPoints(10).setName("Healer").setWeaponDamage(4).build();

    @org.junit.jupiter.api.Test
    void attack() {
        healer.attack(e);
        assertEquals(healer.getLifePoints(), 8);
    }

    @org.junit.jupiter.api.Test
    void defend() {
        healer.defend(e);
        assertEquals(healer.getLifePoints(), 10);
    }

    @org.junit.jupiter.api.Test
    void useConsumable() {
        healer.useConsumable(new Potion.Builder(2).build());
        assertEquals(healer.getManaPoints(), 12);
    }


    @org.junit.jupiter.api.Test
    void increaseArrmor(){
        healer.increaseArmor();
        assertEquals(healer.getArmor(),4);
    }
}