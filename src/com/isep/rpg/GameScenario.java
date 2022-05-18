package com.isep.rpg;

import java.util.List;

public interface GameScenario {

        boolean lose();

        boolean win();

        List<Hero> getHeroList();

        List<Enemy> getEnemyList();

        String attack();

        String defend();

        String consumeFood();

        String consumePotion();
}
