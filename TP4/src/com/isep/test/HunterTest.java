package com.isep.test;

import static org.junit.jupiter.api.Assertions.*;
import com.isep.rpg.Hunter;
import org.junit.jupiter.api.Test;




class HunterTest {
    HunterTest() {
    }

    @Test
    public void test() {
        Hunter hunter = new Hunter();

        while(hunter.lifePoints > 0) {
            if (hunter.arrows > 0) {
                hunter.attack();
                System.out.println("Vous avez infligé " + hunter.weaponDamage + " point(s) de dégât");
                System.out.println("Il vous reste " + hunter.arrows + " flèches");
            } else {
                System.out.println("Il ne vous reste plus aucune flèche");
            }

            hunter.defend();
            if (hunter.lifePoints > 0) {
                System.out.println("Vous disposez de " + hunter.lifePoints + " point(s) de vie");
            } else {
                System.out.println("Il ne vous reste plus de vie. \nVous avez perdu.");
            }
        }

    }
}
