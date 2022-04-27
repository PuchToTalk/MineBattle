package com.isep.test;

import com.isep.rpg.Warrior;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;




class WarriorTest {
    WarriorTest() {
    }

    @Test
    public void test() {
        Warrior warrior = new Warrior();

        while(warrior.lifePoints > 0) {
            warrior.attack();
            System.out.println("Vous avez infligé " + warrior.weaponDamage + " point(s) de dégât");
            warrior.defend();
            if (warrior.lifePoints > 0) {
                System.out.println("Il vous reste " + warrior.lifePoints + " point(s) de vie");
            } else {
                System.out.println("Il ne vous reste plus de vie...\nVous avez perdu le combat");
            }
        }

    }
}
