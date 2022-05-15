package com.isep.rpg;
import com.isep.utils.InputParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Hero> heroes;
    private ArrayList<Enemy> enemies;
    private int playerTurn;
    private InputParser inputParser;

    public Game() {
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }


    public static void printSeperator(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();

    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        printSeperator(66);
        System.out.println("Choix du nombre de héros :");
        int nombreDeHeros = scanner.nextInt();
        this.heroes = new ArrayList();

        int k;
        int z;
        for (k = 1; k <= nombreDeHeros; ++k) {
            Scanner scan = new Scanner(System.in);
            String nomDuHero;
            if (k == 1) {
                printSeperator(66);
                System.out.println("Choix du nom de notre premier héros ?");
                nomDuHero = scan.nextLine();

                do {
                    do {
                        printSeperator(66);
                        System.out.println("Choix du classe de notre / nos héro(s) ? \n1 : Warrior \n2 : Hunter \n3 : Mage\n4 : Healer");
                        z = scan.nextInt();
                    } while (z < 1);
                } while (z > 4);
            } else {
                System.out.println("\n------------------------------------------------------------------\n");
                System.out.println("Choix du nom de notre " + k + "ème héros :");
                nomDuHero = scan.nextLine();

                do {
                    do {
                        System.out.println("\n------------------------------------------------------------------\n");
                        System.out.println("Choix de la classe de ce héro : \n1 : Warrior \n2 : Hunter \n3 : Mage\n4 : Healer");
                        z = scan.nextInt();
                    } while (z < 1);
                } while (z > 4);
            }

            switch (z) {
                case 1 -> {
                    Warrior warrior = new Warrior();
                    warrior.name = nomDuHero;
                    this.heroes.add(warrior);
                }
                case 2 -> {
                    Hunter hunter = new Hunter();
                    hunter.name = nomDuHero;
                    this.heroes.add(hunter);
                }
                case 3 -> {
                    Mage mage = new Mage();
                    mage.name = nomDuHero;
                    this.heroes.add(mage);
                }
                case 4 -> {
                    Healer healer = new Healer();
                    healer.name = nomDuHero;
                    this.heroes.add(healer);
                }
            }
        }


        for (Hero hero : this.heroes) {
            hero.potions = new ArrayList();

            for (k = 0; k < 5; ++k) {
                hero.givePotion();
            }

            hero.lembas = new ArrayList();

            for (k = 0; k < 10; ++k) {
                hero.giveFood();
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
        }

        this.generateCombat(this.heroes.size());


        //this.win();
        //
    }

    public void generateCombat(int nombreDeHerosVivant) {
        this.enemies = new ArrayList();
        Random random = new Random();
        int combatAleatoire = random.nextInt(10);
        BasicEnemy enemy;

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

        // on get l'iterator à partir de la liste enemies

        // tant qu'il existe un élément contenu dans la liste, on le fait apparaître

        for (Enemy enemy1 : this.enemies) {
            System.out.println("Un " + enemy1.name + " apparaît");

        }
        int nombreDeEnemy = this.enemies.size();

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
                    }


                    case 3 -> {
                        System.out.println("");
                        hero.giveFood();
                        System.out.println("Il vous reste plus que " + hero.lembas.size() + " unité(s) de food");
                        hero.lifePoints += hero.lifePoints/3;
                        System.out.println("Vous avez désormais " + hero.lifePoints + " de point(s) de PV" );

                    }
                    case 4 -> {
                        System.out.println("");
                        hero.givePotion();
                        System.out.println("Il vous reste plus que " + hero.potions.size() + " unité(s) de potion");
                        hero.manaPoints += hero.manaPoints/3;
                        System.out.println("Vous avez désormais " + hero.manaPoints + " de point(s) de Mana" );
                    }
                }
            }

        }
    }

    public void choixRecompense(int NombreHero) {
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
                }
                case 2 -> {
                    System.out.println("");
                    System.out.println("Vous avez choisi d'augmenter votre arme de 1 point");
                }
                case 3 -> {
                    System.out.println("");
                    System.out.println("Vous avez décidé d'augmenté le nombre de potions de +1");
                }
                case 4 -> {
                    System.out.println("");
                    System.out.println("Vous avez décidé d'augmenter le nombre de nourriture de +1");
                }
                case 5 -> {
                    System.out.println("");
                    System.out.println("Vous avez décidé d'augmenter la quantité de flèches de +1");
                }
                case 6 -> {
                    System.out.println("");
                    System.out.println("Vous avez décidé de diminuer le coût en mana de -1");
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
