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

    public static void clearConsole(){
        for(int i=0; i<50;i++)
            System.out.println();
    }


    public static void printSeperator(int n){
        for(int i=0; i<n; i++)
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
        //this.choixHero();
        //this.choixRecompense();
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
            for (int i=0; i<nombreDeHerosVivant; i++) {
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
                    System.out.println("Que comptez-vous faire? : \n1 : Attaquer \n2 : Se défendre \n3 : Utiliser un consommable \n4 : Fuir");
                    int choixDecision = scanner.nextInt();

                    switch (choixDecision) {
                        case 1 -> {
                            System.out.println("Quel ennemi attaquez-vous ? : ");
                            int positionEnemy = scanner.nextInt();

                            if (positionEnemy -1 <= this.enemies.size()) {

                                Enemy mob = this.enemies.get(positionEnemy-1);
                                Random alea = new Random();
                                hero.weaponDamage = alea.nextInt(10) + 1;
                                System.out.println(hero.name + " a infligé " + hero.weaponDamage + " point(s) de dégât");
                                mob.lifePoints -= hero.weaponDamage;
                                hero.lifePoints -= mob.weaponDamage;
                                if (hero.lifePoints > 0) {
                                    System.out.println(hero.name + " a encore " + hero.lifePoints + " point(s) de vie");
                                } else {
                                    System.out.println(hero.name + " n'a plus de vie...\n");
                                    nombreDeHerosVivant -= 1;
                                    if (nombreDeHerosVivant == 0) {
                                        System.out.println("Il ne vous reste plus de vie...\nVous avez perdu le combat");
                                        break;
                                    }
                                }

                                if (mob.lifePoints > 0) {
                                    System.out.println(mob.name + " vous fait face et a encore " + mob.lifePoints + " de PV");
                                } else {
                                    System.out.println(mob.name + " a été vaincu");
                                    nombreDeEnemy -= 1;
                                    this.enemies.remove(positionEnemy-1);

                                }

                                if (nombreDeEnemy == 0 && mob.lifePoints <= 0) {
                                    System.out.println("Vous avez vaincu tous les enemies \nVous avez remporté le combat !!! ");

                                    // clearConsole()
                                    break;
                                }
                            }

                        }
                        case 2 -> {
                            System.out.println("");
                        }
                        case 3 -> {
                            System.out.println("");
                        }
                        case 4 -> {
                            System.out.println("");
                        }
                    }
                }

            }
  //      }




/*
    public void choixHero() {
        for (Enemy enemy : this.enemies){
            for (Hero hero : this.heroes) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n------------------------------------------------------------------\n");
                System.out.println("Que comptez-vous faire? : \n1 : Attaquer \n2 : Se défendre \n3 : Utiliser un consommable \n4 : Fuir");
                int choixDecision = scanner.nextInt();

                switch (choixDecision) {
                    case 1 -> {
                        Random random = new Random();
                        hero.weaponDamage = random.nextInt(10) + 1;
                        System.out.println("Vous avez infligé " + hero.weaponDamage + " point(s) de dégât");
                        enemy.lifePoints -= hero.weaponDamage;
                        hero.lifePoints -= enemy.weaponDamage;

                        if (hero.lifePoints > 0) {
                            System.out.println("Il vous reste " + hero.lifePoints + " point(s) de vie");
                        } else {
                            System.out.println("Il ne vous reste plus de vie...\nVous avez perdu le combat");
                        }
                    }
                    case 2 -> {
                        System.out.println("");
                    }
                    case 3 -> {
                        System.out.println("");
                    }
                    case 4 -> {
                        System.out.println("");
                    }
                }
            }

        }
    }





    public void choixRecompense(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Quelle récompense choisir ? : \n1 : Armure ++ \n2 : Arme ++ \n3 : Potion HP++ \n4 : Nourriture HP++ \n5 : Potion Qtité ++ \n6 : Nourriture Qtité ++ \n7 : Flèche Qtité ++ \n8 : Coût mana -- \n9 : Sort ++");
        int choixRecomp = scanner.nextInt();
        switch (choixRecomp) {
            case 1 -> {
                System.out.println("");

            }
            case 2 -> {
                System.out.println("");
            }
            case 3 -> {
                System.out.println("");
            }
            case 4 -> {
                System.out.println("");
            }
            case 5 -> {
                System.out.println("");
            }
            case 6 -> {
                System.out.println("");
            }
            case 7 -> {
                System.out.println("");
            }
            case 8 -> {
                System.out.println("");
            }
            case 9 -> {
                System.out.println("");
             }
            }

        }
*/
    }



    private void turnTime(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

