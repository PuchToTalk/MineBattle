package com.isep.test;


/**
 * @auteur  (Paul)
 * @version (v.ok - 27/05/2022)
 */


import com.isep.rpg.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    Enemy e = new BasicEnemy.Builder(10).setName("Ghast").setDamage(4).build();
    Mage mage= new Mage.Builder(10).setArmor(3).setManaCost(2).setManaPoints(10).setName("Mage").setWeaponDamage(4).build();


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

    @Test
    void useConsumable() {
        mage.useConsumable(new Potion.Builder(2).build());
        assertEquals(12, mage.getManaPoints());
        mage.useConsumable(new Food.Builder(4).build());
        assertEquals(14, mage.getLifePoints());
    }
}