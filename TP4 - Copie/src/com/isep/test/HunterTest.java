package com.isep.test;
import com.isep.rpg.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @auteur  (Paul)
 * @version (v.ok - 27/05/2022)
 */


class HunterTest {
    Enemy e = new Boss.Builder(10).setName("Squelette").setDamage(5).build();
    Hunter hunter = new Hunter.Builder(10).setArmor(3).setArrows(10).setName("Hunter").setWeaponDamage(4).build();

    @org.junit.jupiter.api.Test
    void attack() {
        hunter.attack(e);
        assertEquals(hunter.getLifePoints(), 5);
    }

    @org.junit.jupiter.api.Test
    void defend() {
        hunter.defend(e);
        assertEquals(hunter.getLifePoints(), 8);
    }

    @org.junit.jupiter.api.Test
    void useConsumable() {
        hunter.useConsumable(new Food.Builder(5).build());
        assertEquals(hunter.getLifePoints(), 15);
    }


    @org.junit.jupiter.api.Test
    void increaseArrmor(){
        hunter.increaseArmor();
        assertEquals(hunter.getArmor(),4);
    }
}
