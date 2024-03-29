package com.isep.utils;

import com.isep.rpg.Enemy;
import com.isep.rpg.Hero;

import java.util.List;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 17/05/2022)
 * Fight de base
 */

public interface InputParser {

        boolean lose();

        boolean win();

        List<Hero> getHeroList();

        List<Enemy> getEnemyList();

        String attack();

        String defend();

        String consumeFood();

        String consumePotion();

        /**
         * @auteur  (Paul)
         * @version (v.o2 - 23/05/2022)
         * Pour la partie des récompenses
         */

        String increaseArmor();

        String increaseWeaponDamage();

        String increaseFoodAndPotionEffectiveness();

        String increasePotionAndFoodNumber();

        String increaseArrows();

        String decreaseManaCost();

        String newCombat();
}
