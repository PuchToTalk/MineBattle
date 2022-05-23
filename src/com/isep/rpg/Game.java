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
     * (à continuer)
     * **/


    public String newCombat() {
        enemies.clear();
        loadEnemies(totalPlayers);
        enemyTurn = getEnemyTurn();
        return "Chargement du combat";
    }
        // Condition pour apparition d'un BOSS (1/10 de proba)
        if (combatAleatoire == 0) {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Un boss apparaît et il est accompagné de ses deux serviteurs !\nDébut du combat");
            Enemy boss = new Boss();
            enemy = new BasicEnemy();
            Enemy enemy2 = new BasicEnemy();
            this.enemies.add(boss);
            this.enemies.add(enemy);
            this.enemies.add(enemy2);
        }
        // Condition pour apparition de ENEMY basique (9/10 de proba)
        else {
            System.out.println(nombreDeHerosVivant + " monstre(s) vous fait/font face !\nDébut du combat");

            for (int i = 0; i < nombreDeHerosVivant; ++i) {
                enemy = new BasicEnemy();
                this.enemies.add(enemy);
            }
        }


            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Nom du héro : " + hero.name);
            System.out.println(hero.getClass());
            System.out.println("Nombre de HP : " + hero.lifePoints);
            System.out.println("Quantité de potions : " + hero.potions.size());
            System.out.println("Quantité de nourritures : " + hero.lembas.size());
            System.out.println("\n------------------------------------------------------------------\n");
    turnTime(2);
            Game.clearConsole();



        for (Enemy enemy1 : this.enemies) {
            System.out.println("Un " + enemy1.name + " apparaît");

        }
        int nombreDeEnemy = this.enemies.size();


        private void loadEnemies(int x){
            enemies.add();
        }

        while (nombreDeHerosVivant > 0 && nombreDeEnemy > 0) {
            for (int i = 0; i < nombreDeHerosVivant; i++) {
                Hero hero = this.heroes.get(i);
                //    for (Hero hero : this.heroes) {
                //        for (Enemy mob : this.enemies) {

                Scanner scanner = new Scanner(System.in);

                printSeperator(66);
                System.out.println("Nom du héro : " + hero.name);
                System.out.println("Nombre de HP : " + hero.lifePoints);
                System.out.println("Quantité de potions : " + hero.potions.size());
                System.out.println("Quantité de nourritures : " + hero.lembas.size());
                printSeperator(66);
                System.out.println("Que comptez-vous faire? : \n1 : Attaquer \n2 : Se défendre \n3 : Food HP \n4 : Potion Mana ");
                int choixDecision = scanner.nextInt();

                switch (choixDecision) {
                    case 1 -> {
                        System.out.println("Quel ennemi attaquez-vous ? : ");
                        int positionEnemy = scanner.nextInt();
                        if (positionEnemy - 1 <= this.enemies.size()) {

                            Enemy mob = this.enemies.get(positionEnemy - 1);
                            Random alea = new Random();
                            // hero.weaponDamage = alea.nextInt(10) + 1;
                            hero.attack();
                            System.out.println(hero.name + " a infligé " + hero.weaponDamage + " point(s) de dégât");
                            mob.lifePoints -= hero.weaponDamage;
                            hero.lifePoints -= mob.weaponDamage;
                            if (hero.lifePoints > 0) {
                                System.out.println(hero.name + " a subi " + mob.weaponDamage + " de dégâts et a encore " + hero.lifePoints + " point(s) de vie");
                            } else {
                                int pos = this.heroes.lastIndexOf(hero.name);
                                System.out.println(hero.name + " a subi " + mob.weaponDamage + " de dégâts");
                                System.out.println(hero.name + " n'a plus de vie...\n");
                                nombreDeHerosVivant -= 1;
                                System.out.println(pos + 1);
                                this.heroes.remove(pos + 1);
                                if (nombreDeHerosVivant == 0 && (this.heroes.isEmpty())) {
                                    Game.clearConsole();
                                    System.out.println("Il ne vous reste plus de vie...\nVous avez perdu le combat");

                                    break;
                                }
                            }

                            if (mob.lifePoints > 0) {
                                System.out.println(mob.name + " vous fait face et a encore " + mob.lifePoints + " de PV");
                            } else {
                                System.out.println(mob.name + " a été vaincu");
                                nombreDeEnemy -= 1;
                                this.enemies.remove(positionEnemy - 1);

                            }

                            if (nombreDeEnemy == 0 && (this.enemies.isEmpty())) {
                                System.out.println("Vous avez vaincu tous les ennemies \nVous avez remporté le combat !!! ");
                                // wint()
                                turnTime(4);
                                this.choixRecompense(this.heroes.size());
                                // clearConsole()
                                break;
                            }


                            /** Réécriture de la fonction défense **/
                            public String attack () {

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
                        }
                    }


                    case 2 -> {
                        System.out.println("Vous avez choisi de vous défendre ");

                        int actu = hero.lifePoints;
                        hero.defend();
                        int subi = actu - hero.lifePoints;

                        if (hero.lifePoints > 0) {
                            System.out.println(hero.name + " a subi " + subi + " de dégâts");
                            System.out.println(hero.name + " a toujours " + hero.lifePoints + " de point(s) de vie");
                        } else {
                            int pos = this.heroes.lastIndexOf(hero.name);
                            System.out.println(hero.name + " a subi " + subi + " de dégâts");
                            System.out.println(hero.name + " n'a plus de vie...\n");
                            nombreDeHerosVivant -= 1;
                            System.out.println(pos + 1);
                            this.heroes.remove(pos + 1);
                            if (nombreDeHerosVivant == 0 && (this.heroes.isEmpty())) {
                                Game.clearConsole();
                                System.out.println("Il ne vous reste plus de vie...\nVous avez perdu le combat");

                                break;

                            }
                        }


                        /** Réécriture de la fonction défense **/

                        public String defend () {
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


                            case 3 -> {
                                System.out.println("");
                                hero.giveFood();
                                System.out.println("Il vous reste plus que " + hero.lembas.size() + " unité(s) de food");
                                hero.lifePoints += hero.lifePoints / 3;
                                System.out.println("Vous avez désormais " + hero.lifePoints + " de point(s) de PV");

                                public String consumeFood () {
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

                            }
                            case 4 -> {
                                System.out.println("");
                                hero.givePotion();
                                System.out.println("Il vous reste plus que " + hero.potions.size() + " unité(s) de potion");
                                hero.manaPoints += hero.manaPoints / 3;
                                System.out.println("Vous avez désormais " + hero.manaPoints + " de point(s) de Mana");

                                public String consumePotion () {
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
                        }
                    }
                }
            }

            public void choixRecompense ( int NombreHero){
                for (Hero hero : this.heroes) {

                    System.out.println("\nVoici vos STATS actuellement : \n");
                    turnTime(1);
                    System.out.println("\n------------------------------------------------------------------\n");
                    System.out.println("Nom du héro : " + hero.name);
                    System.out.println(hero.getClass());
                    System.out.println("Nombre de HP : " + hero.lifePoints);
                    System.out.println("Armure : " + hero.armor);
                    System.out.println("Quantité de potions : " + hero.potions.size());
                    System.out.println("Quantité de nourritures : " + hero.lembas.size());
                    System.out.println("\n------------------------------------------------------------------\n");
                    turnTime(3);


                    System.out.println("Quelle récompense choisir ? : \n1 : Armure ++ \n2 : Arme ++ \n3 : Potion Qtité ++ \n4 : Nourriture Qtité ++ \n5 : Flèche Qtité ++  \n6 : Coût Mana");
                    Scanner scanner = new Scanner(System.in);
                    int choixRecomp = scanner.nextInt();
                    printSeperator(66);
                    switch (choixRecomp) {
                        case 1 -> {
                            System.out.println("Vous avez choisi d'augmenter votre armure de 1 point");
                            hero.armor += 1;
                            System.out.println(hero.armor);

                            public String increaseArmor () {
                                String res = getHeroByTurn().increaseArmor();
                                nextHero();
                                return res;

                            }
                            case 2 -> {
                                System.out.println("");
                                System.out.println("Vous avez choisi d'augmenter votre arme de 1 point");

                                public String increaseWeaponDamage () {
                                    String res = getHeroByTurn().increaseWeaponDamage();
                                    nextHero();
                                    return res;
                                }

                            }
                            case 3 -> {
                                System.out.println("");
                                System.out.println("Vous avez décidé d'augmenté l'efficacité des food & potion ");

                                public String increaseFoodAndPotionEffectiveness () {
                                    String res = getHeroByTurn().increasePotionAndFoodEffectiveness();
                                    nextHero();
                                    return res;
                                }
                            }
                            case 4 -> {
                                System.out.println("");
                                System.out.println("Vous avez décidé d'augmenter le nombre de food & potion");

                                public String increasePotionAndFoodNumber () {
                                    String res = getHeroByTurn().increasePotionAndFoodNumber();
                                    nextHero();
                                    return res;
                                }

                            }
                            case 5 -> {
                                System.out.println("");
                                System.out.println("Vous avez décidé d'augmenter la quantité de flèches de +1");

                                public String increaseArrows () {
                                    if (getHeroByTurn() instanceof Hunter) {
                                        String res = getHeroByTurn().increaseArrows();
                                        nextHero();
                                        return res;
                                    }
                                    return "Navré, ceci est réservé à la classe Hunter";
                                }

                            }
                            case 6 -> {
                                System.out.println("");
                                System.out.println("Vous avez décidé de diminuer le coût en mana de -1");

                                public String decreaseManaCost () {
                                    if (getHeroByTurn() instanceof SpellCaster) {
                                        String res = getHeroByTurn().decraseManaCost();
                                        nextHero();
                                        return res;
                                    }
                                    return "Navré, ceci est réservé aux héros SpellCaster";
                                }


                            }
                        }
                    }
                }
            }

            private void turnTime ( int time){
                try {
                    Thread.sleep(time * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        }
}