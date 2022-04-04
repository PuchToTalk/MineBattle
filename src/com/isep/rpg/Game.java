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

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Choix du nombre de héros :");
        int nombreDeHeros = scanner.nextInt();
        this.heroes = new ArrayList();

        int k;
        int z;
        for (k = 1; k <= nombreDeHeros; ++k) {
            Scanner scan = new Scanner(System.in);
            String nomDuHero;
            if (k == 1) {
                System.out.println("\n------------------------------------------------------------------\n");
                System.out.println("Choix du nom de notre premier héros ?");
                nomDuHero = scan.nextLine();

                do {
                    do {
                        System.out.println("\n------------------------------------------------------------------\n");
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
        }

        this.generateCombat(this.heroes.size());
        //this.choixHero();
        //this.choixRecompense();
    }

    public void generateCombat(int nombreDeHerosVivant) {
        this.enemies = new ArrayList();
        Random random = new Random();
        int combatAleatoire = random.nextInt(3);
        BasicEnemy enemy;
        if (combatAleatoire == 0) {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Un boss apparaît et il est accompagné de ses deux serviteurs !\nDébut du combat");
            Enemy boss = new Boss();
            enemy = new BasicEnemy();
            Enemy enemy2 = new BasicEnemy();
            this.enemies.add(boss);
            this.enemies.add(enemy);
            this.enemies.add(enemy2);
        } else {
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

        /*
        System.out.println("\n------------------------------------------------------------------\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que comptez-vous faire? : \n1 : Attaquer \n2 : Se défendre \n3 : Utiliser un consommable \n4 : Fuir");
        int choixDecision = scanner.nextInt();
        System.out.println(choixDecision);

        */
        for (Enemy mob : this.enemies) {
            for (Hero hero : this.heroes) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n------------------------------------------------------------------\n");
                System.out.println("Que comptez-vous faire? : \n1 : Attaquer \n2 : Se défendre \n3 : Utiliser un consommable \n4 : Fuir");
                int choixDecision = scanner.nextInt();

                switch (choixDecision) {
                    case 1 -> {
                        Random alea = new Random();
                        hero.weaponDamage = alea.nextInt(10) + 1;
                        System.out.println("Vous avez infligé " + hero.weaponDamage + " point(s) de dégât");
                        mob.lifePoints -= hero.weaponDamage;
                        hero.lifePoints -= mob.weaponDamage;

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
}

