package com.isep.rpg;
import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.*;

/**
 * @PS : Changements apportés :
 * GameLogic optimisation (1)
 *
 * @auteur(s)  (Paul)
 * @version (v.o1 - 17/05/2022)
 * Apport fonctions
 * Lose / win / attack / defend / ConsumeFood / ConsumePotion
**/



public class Game implements GameScenario {
    private List<Hero> heroes;
    private List<Enemy> enemies;
    private int totalPlayers;
    private int playerTurn;
    private int enemyTurn;
    private final Random random = new Random();
    ;

    public Game(int x, int mage, int healer, int hunter, int warrior) {
        totalPlayers = x;
        heroes = new ArrayList<>();
        enemies = new ArrayList<>();
        playerTurn = random.nextInt(x);
        enemyTurn = random.nextInt(x);
        loadHeroes(mage, healer, hunter, warrior); // Les 4 classes de héros
        loadEnemies(totalPlayers); // = le nbre de héro choisi
    }

    /** Pour le RPG console text-based **/

    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }


    public static void printSeperator(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    private void turnTime ( int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




        /** Infos sur var de départ (sur les Listes / Round / Position ) **/

    @Override
    public List<Enemy> getEnemyList() {
        return this.enemies;
    }

    @Override
    public List<Hero> getHeroList() {
        return this.heroes;
    }

    public int getEnemyTurn() {
        return this.enemyTurn;
    }

    private int getIndex(int x, int i) {
        return (x + i) % totalPlayers;
    }

    private int getIndex() {
        return playerTurn % totalPlayers;
    }

    private Enemy getEnemyByIndex(int index) {
        return this.enemies.get(index);
    }

    /** --- Infos par tour ---  **/

    public Enemy getEnemyByTurn() {
        return enemies.get(enemyTurn);
    }

    private Hero getHeroByTurn() {
        return heroes.get(getIndex());
    }

    private void changeTurn() {
        playerTurn++;
        enemyTurn = getET();
    }

    private int getET() {
        int x = random.nextInt(totalPlayers);
        for (int i = 0; i < totalPlayers; i++) {
            if (isLive(getEnemyByIndex(getIndex(x, i)))) {
                return getIndex(x + i, totalPlayers);
            }
        }
        return -1;
    }

    public int getPlayerTurn() {
        return this.playerTurn % totalPlayers;
    }

    private void nextHero() {
        playerTurn++;
        playerTurn %= totalPlayers;
        System.out.println(playerTurn + "      Continuer");
    }

    /** Infos sur statut du perso Héro / Enemy **/


    private boolean isLive(Hero hero) {
        return hero.getLifePoints() > 0;
    }

    private boolean isLive(Enemy enemy) {
        return enemy.getLifePoints() > 0;
    }

    private boolean isHeroesLeft() {
        int h = 0;
        for (Hero hero : heroes) {
            if (isLive(hero)) {
                h++;
            }
        }

        System.out.println("Heros: " + h);

        return h > 0;
    }

    private boolean isEnemiesLeft() {
        int e = 0;
        for (Enemy enemy : enemies) {
            if (isLive(enemy)) {
                e++;
            }
        }

        System.out.println("Ennemis: " + e);

        return e > 0;
    }


    /** Infos sur conditions de victoires / défaites  **/


    @Override
    public boolean win() {
        if (!isEnemiesLeft() && isHeroesLeft()) {
            playerTurn = 0;
            enemyTurn = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean lose() {
        System.out.println("Statut : " + " Héros est-il vivant ? " + isHeroesLeft() + " Ennemis est-il vivant ? " + isEnemiesLeft());
        return !isHeroesLeft() && isEnemiesLeft();
    }


    /** Infos sur Actions Heros  **/


    @Override
    public String consumeFood() {
        if (isLive(getHeroByTurn())) {
            List<Food> foods = getHeroByTurn().getLembas();
            if (foods.size() <= 0) {
                return "Plus de Food disponible";
            }
            getHeroByTurn().useConsumable(foods.get(0));
            foods.remove(0);
            changeTurn();
            return "Food a été utilisé";
        } else {
            changeTurn();
            return consumePotion();
        }
    }


    @Override
    public String consumePotion() {
        if (isLive(getHeroByTurn())) {
            List<Potion> potions = getHeroByTurn().getPotions();
            if (getHeroByTurn() instanceof SpellCaster) {
                if (potions.size() <= 0) {
                    return "Plus de Potion disponible";
                }
                getHeroByTurn().useConsumable(potions.get(0));
                potions.remove(0);
                changeTurn();
                return "Potion a été utilisé";
            }
            return "Navré, ceci est réservé aux héros Spell-Caster";
        } else {
            changeTurn();
            return consumePotion();
        }
    }

/**
 * @auteur(s)  (Paul)
 * @version (v.o2 - 22/05/2022)
 * Paramétrage stats & chargement des données & règles connues : OK
 * Ajout de loading heroes & enemies
 * **/


    // chargement selon nombre de héro choisi pour chaque classe
    private void loadHeroes(int mage, int healer, int hunter, int warrior) {
        if (mage > 0) {
            for (int i = 0; i < mage; i++) {
                heroes.add(
                        new Mage.Builder(10)
                                .setName("Mage-" + (i + 1))
                                .setManaCost(1)
                                .setManaPoints(10)
                                .setArmor(3)
                                .setWeaponDamage(3)
                                .build()
                );
            }
        }
        if (healer > 0) {
            for (int i = 0; i < healer; i++) {
                heroes.add(
                        new Healer.Builder(10)
                                .setName("Healer-" + (i + 1))
                                .setManaCost(1)
                                .setManaPoints(10)
                                .setArmor(3)
                                .setWeaponDamage(3)
                                .build()
                );
            }
        }
        if (hunter > 0) {
            for (int i = 0; i < hunter; i++) {
                heroes.add(
                        new Hunter.Builder(10)
                                .setName("Hunter-" + (i + 1))
                                .setArrows(10).setArmor(3)
                                .setWeaponDamage(3)
                                .build()
                );
            }
        }
        if (warrior > 0) {
            for (int i = 0; i < warrior; i++) {
                heroes.add(
                        new Warrior.Builder(10)
                                .setName("Warrior-" + (i + 1))
                                .setArmor(3)
                                .setWeaponDamage(3)
                                .build()
                );
            }
        }
    }
    // chargement selon nombre de ennemi en fct du nbre de héros
        private void loadEnemies(int x) {
             enemies.add(
                  new Boss.Builder(15)
                        .setName("Ender Dragon")
                        .setDamage(2)
                        .build()
         );
         x--;
         if (x > 0) {
               for (int i = 0; i < x; i++) {
                   enemies.add(
                           new BasicEnemy.Builder(10)
                                   .setName("Zombie-" + (i + 1))
                                   .setDamage(1)
                                  .build()
                   );
               }
          }
        }




    /**
     * @auteur(s)  (Paul)
     * @version (v.o2 - 23/05/2022)
     * Chargement nveau combat : simulation
     * avec intéractions de bases (attack / defend / useConsomable : Potion / Food)
     * **/


    public String newCombat() {
        enemies.clear();
        loadEnemies(totalPlayers);
        enemyTurn = getEnemyTurn();
        return "Chargement du combat";
    }


    @Override
    public String attack() {

        if (isLive(getHeroByTurn())) {
            if (getHeroByTurn() instanceof SpellCaster) {
                if (getHeroByTurn().getManaPoints() < getHeroByTurn().getManaCost()) {
                    getHeroByTurn().attack(getEnemyByTurn());
                    return "Navré, ce héro ne peut plus attaquer, Plus de Mana en réserve";
                }
            }
            getHeroByTurn().attack(getEnemyByTurn());
            if (win() || lose()) {
                return "Jeu terminé";
            }
            getEnemyByTurn().damage(getHeroByTurn());
            changeTurn();
            return "Attaque réussi";
        } else {
            changeTurn();
            return consumePotion();
        }
    }



    @Override
    public String defend() {
        if (isLive(getHeroByTurn())) {
            getHeroByTurn().defend(getEnemyByTurn());
            if (win() || lose()) {
                return "Jeu terminé";
            }
            changeTurn();
            return "Defense";
        } else {
            changeTurn();
            return defend();
        }
    }


    @Override
    public String consumeFood() {
        if (isLive(getHeroByTurn())) {
            List<Food> foods = getHeroByTurn().getLembas();
            if (foods.size() <= 0) {
                return "Plus de Food disponible";
            }
            getHeroByTurn().useConsumable(foods.get(0));
            foods.remove(0);
            changeTurn();
            return "Food a été utilisé";
        } else {
            changeTurn();
            return consumePotion();
        }
    }


    @Override
    public String consumePotion() {
        if (isLive(getHeroByTurn())) {
            List<Potion> potions = getHeroByTurn().getPotions();
            if (getHeroByTurn() instanceof SpellCaster) {
                if (potions.size() <= 0) {
                    return "Plus de Potion disponible";
                }
                getHeroByTurn().useConsumable(potions.get(0));
                potions.remove(0);
                changeTurn();
                return "Potion a été utilisé";
            }
            return "Navré, ceci est réservé aux héros Spell-Caster";
        } else {
            changeTurn();
            return consumePotion();
        }
    }




}