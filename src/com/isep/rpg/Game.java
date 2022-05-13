package com.isep.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */


public class Game implements ScenarioGame {
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
        loadHeroes(mage, healer, hunter, warrior);
        loadEnemies(totalPlayers);
    }

    @Override
    public boolean lose() {
        System.out.println("Lose: " + " H/E" + isHeroesLeft() + "  " + isEnemiesLeft());
        return !isHeroesLeft() && isEnemiesLeft();
    }


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
    public List<Hero> getHeroList() {
        return this.heroes;
    }


    @Override
    public String attack() {

        if (isLive(getHeroByTurn())) {
            if (getHeroByTurn() instanceof SpellCaster) {
                if (getHeroByTurn().getManaPoints() < getHeroByTurn().getManaCost()) {
                    getHeroByTurn().attack(getEnemyByTurn());
                    return "Sorry, Hero cannot cast spell, Because Mana-Points less Mana-Cost";
                }
            }
            getHeroByTurn().attack(getEnemyByTurn());
            if (win() || lose()) {
                return "Game Finished";
            }
            getEnemyByTurn().damage(getHeroByTurn());
            changeTurn();
            return "Attacked Successfully";
        } else {
            changeTurn();
            return consumePotion();
        }
    }

    @Override
    public List<Enemy> getEnemyList() {
        return this.enemies;
    }


    @Override
    public String defend() {
        if (isLive(getHeroByTurn())) {
            getHeroByTurn().defend(getEnemyByTurn());
            if (win() || lose()) {
                return "Game Finished";
            }
            changeTurn();
            return "Defend";
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
                return "Not Enough Potions Left";
            }
            getHeroByTurn().useConsumable(foods.get(0));
            foods.remove(0);
            changeTurn();
            return "Successfully Food Taken";
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
                    return "Not Enough Potions Left:";
                }
                getHeroByTurn().useConsumable(potions.get(0));
                potions.remove(0);
                changeTurn();
                return "Potion Successfully Taken";
            }
            return "Sorry, Hero does not Belong to Spell-Caster Family";
        } else {
            changeTurn();
            return consumePotion();
        }
    }

    @Override
    public String increaseArmor() {
        String res =getHeroByTurn().increaseArmor();
        nextHero();
        return res;
    }

    @Override
    public String increaseWeaponDamage() {
        String res = getHeroByTurn().increaseWeaponDamage();
        nextHero();
        return res;
    }

    @Override
    public String increaseFoodAndPotionEffectiveness() {
        String res = getHeroByTurn().increasePotionAndFoodEffectiveness();
        nextHero();
        return res;
    }

    @Override
    public String increasePotionAndFoodNumber() {
        String res = getHeroByTurn().increasePotionAndFoodNumber();
        nextHero();
        return res;
    }

    @Override
    public String increaseArrows() {
        if (getHeroByTurn() instanceof Hunter) {
            String res = getHeroByTurn().increaseArmor();
            nextHero();
            return res;
        }
        return "Sorry, Hero Does not belong to the Hunter Family";
    }

    @Override
    public String decreaseManaCost() {
        if (getHeroByTurn() instanceof SpellCaster) {
            String res = getHeroByTurn().decraseManaCost();
            nextHero();
            return res;
        }
        return "Sorry, Hero Does not belong to the SpellCaster Family";
    }

    @Override
    public String newCombat() {
        enemies.clear();
        loadEnemies(totalPlayers);
        enemyTurn = getEnemyTurn();
        return "Ready Battle Field";
    }


    public int getEnemyTurn() {
        return this.enemyTurn;
    }


    public int getPlayerTurn() {
        return this.playerTurn % totalPlayers;
    }


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

    private void loadEnemies(int x) {
        enemies.add(
                new Boss.Builder(15)
                        .setName("Boss Fighter")
                        .setDamage(2)
                        .build()
        );
        x--;
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                enemies.add(
                        new BasicEnemy.Builder(10)
                                .setName("Mini-" + (i + 1))
                                .setDamage(1)
                                .build()
                );
            }
        }
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

    public Enemy getEnemyByTurn() {
        return enemies.get(enemyTurn);
    }

    private Hero getHeroByTurn() {
        return heroes.get(getIndex());
    }

    private boolean isHeroesLeft() {
        int h = 0;
        for (Hero hero : heroes) {
            if (isLive(hero)) {
                h++;
            }
        }

        System.out.println("Heroes: " + h);

        return h > 0;
    }

    private boolean isEnemiesLeft() {
        int e = 0;
        for (Enemy enemy : enemies) {
            if (isLive(enemy)) {
                e++;
            }
        }

        System.out.println("Enemies: " + e);

        return e > 0;
    }

    private void changeTurn() {
        playerTurn++;
        enemyTurn = getET();
    }

    private Enemy getEnemyByIndex(int index) {
        return this.enemies.get(index);
    }

    private boolean isLive(Hero hero) {
        return hero.getLifePoints() > 0;
    }

    private boolean isLive(Enemy enemy) {
        return enemy.getLifePoints() > 0;
    }

    private int getIndex(int x, int i) {
        return (x + i) % totalPlayers;
    }

    private int getIndex() {
        return playerTurn % totalPlayers;
    }

    private void nextHero() {
        playerTurn++;
        playerTurn %= totalPlayers;
        System.out.println(playerTurn + "      Next");
    }

}
