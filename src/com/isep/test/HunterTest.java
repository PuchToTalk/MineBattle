package com.isep.test;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */

import com.isep.rpg.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    Enemy e = new Boss.Builder(10).setName("Mini Titan").setDamage(5).build();
    Hunter hunter = new Hunter.Builder(10).setArmor(3).setArrows(10).setName("Healer").setWeaponDamage(4).build();

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

