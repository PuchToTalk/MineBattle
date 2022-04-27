package com.isep.test;

import static org.junit.jupiter.api.Assertions.*;
import com.isep.rpg.Mage;
import org.junit.jupiter.api.Test;



class MageTest {
    MageTest() {
    }

    @Test
    public void test() {
        Mage mage = new Mage();

        while(mage.lifePoints > 0) {
            if (mage.manaPoints >= mage.manaConsomme) {
                mage.attack();
                System.out.println("Vous avez infligé " + mage.weaponDamage + " point(s) de dégât");
                System.out.println("Il vous reste " + mage.manaPoints + " point(s) de mana");
            } else {
                System.out.println("Il n'y a plus de point de mana");
            }

            mage.defend();
            if (mage.lifePoints > 0) {
                System.out.println("Vous disposez de " + mage.lifePoints + " point(s) de vie");
            } else {
                System.out.println("Il ne vous reste plus de vie. \nVous avez perdu.");
            }
        }

    }
}
