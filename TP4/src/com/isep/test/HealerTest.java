package com.isep.test;

import static org.junit.jupiter.api.Assertions.*;
import com.isep.rpg.Healer;
import org.junit.jupiter.api.Test;

class HealerTest {
    HealerTest() {
    }

    @Test
    public void test() {
        Healer healer = new Healer();
        int vieMax = healer.lifePoints;

        while(healer.lifePoints > 0) {
            if (healer.lifePoints <= vieMax - vieMax / 2) {
                if (healer.manaPoints >= healer.manaConsomme) {
                    healer.attack();
                    healer.lifePoints += healer.weaponDamage;
                    if (healer.lifePoints > vieMax) {
                        int soin = vieMax - healer.lifePoints + healer.weaponDamage;
                        healer.lifePoints = vieMax;
                        System.out.println("Gain de " + soin + " point(s) de vie");
                    } else {
                        System.out.println("Gain de " + healer.weaponDamage + " point(s) de vie");
                    }

                    System.out.println("Il vous reste " + healer.manaPoints + " point(s) de mana");
                } else {
                    System.out.println("Il n'y a plus de point de mana");
                }
            }

            healer.defend();
            if (healer.lifePoints > 0) {
                System.out.println("Vous disposez de " + healer.lifePoints + " points(s)de vie");
            } else {
                System.out.println("Il ne vous reste plus de vie. \nVous avez perdu.");
            }
        }

    }
}
