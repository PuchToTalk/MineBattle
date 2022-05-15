package com.isep.test;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


import com.isep.rpg.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    Enemy e = new BasicEnemy.Builder(10).setName("Mini Titan").setDamage(4).build();
    Mage  mage= new Mage.Builder(10).setArmor(3).setManaCost(2).setManaPoints(10).setName("Healer").setWeaponDamage(4).build();


    @Test
    void attack() {
        mage.attack(e);
        assertEquals(8,mage.getManaPoints());
    }

    @Test
    void defend() {
        mage.defend(e);
        assertEquals(9, mage.getLifePoints());
    }

