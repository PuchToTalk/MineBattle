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
        System.out.println("Choisissez le nombre de héros qui participeront à l'aventure !");
        int nombreDeHeros = scanner.nextInt();
        this.heroes = new ArrayList();

        int k;
        for(k = 1; k <= nombreDeHeros; ++k) {
            Scanner scan = new Scanner(System.in);
            String nomDuHero;
            if (k == 1) {
                System.out.println("Quelle est le nom du 1er héro ?");
                nomDuHero = scan.nextLine();

                do {
                    do {
                        System.out.println("Quelle est sa classe ? \n1 : Guerrier\n2 : Chasseur\n3 : Mage\n4 : Soigneur");
                        k = scan.nextInt();
                    } while(k < 1);
                } while(k > 4);
            } else {
                System.out.println("Quelle est le nom du " + k + "e héro ?");
                nomDuHero = scan.nextLine();

                do {
                    do {
                        System.out.println("Quelle est sa classe ? \n1 : Guerrier\n2 : Chasseur\n3 : Mage\n4 : Soigneur");
                        k = scan.nextInt();
                    } while(k < 1);
                } while(k > 4);
            }

            switch(k) {
                case 1:
                    Warrior warrior = new Warrior();
                    warrior.name = nomDuHero;
                    this.heroes.add(warrior);
                    break;
                case 2:
                    Hunter hunter = new Hunter();
                    hunter.name = nomDuHero;
                    this.heroes.add(hunter);
                    break;
                case 3:
                    Mage mage = new Mage();
                    mage.name = nomDuHero;
                    this.heroes.add(mage);
                    break;
                case 4:
                    Healer healer = new Healer();
                    healer.name = nomDuHero;
                    this.heroes.add(healer);
            }
        }

        Iterator var11 = this.heroes.iterator();

        while(var11.hasNext()) {
            Hero hero = (Hero)var11.next();
            hero.potions = new ArrayList();

            for(k = 0; k < 5; ++k) {
                hero.givePotion();
            }

            hero.lembas = new ArrayList();

            for(k = 0; k < 10; ++k) {
                hero.giveFood();
            }

            System.out.println("Nom du héro : " + hero.name);
            System.out.println(hero.getClass());
            System.out.println("Points de vie : " + hero.lifePoints);
            System.out.println("Nombre de potions : " + hero.potions.size());
            System.out.println("Quantité de nourritures : " + hero.lembas.size());
        }

        this.generateCombat(this.heroes.size());
    }

    public void generateCombat(int nombreDeHerosVivant) {
        this.enemies = new ArrayList();
        Random random = new Random();
        int combatAleatoire = random.nextInt(5);
        BasicEnemy enemy;
        if (combatAleatoire == 0) {
            System.out.println("Oh ! Vous êtes tombé sur un boss avec deux sbires !\nPréparez-vous à combattre");
            Enemy boss = new Boss();
            enemy = new BasicEnemy();
            Enemy enemy2 = new BasicEnemy();
            this.enemies.add(boss);
            this.enemies.add(enemy);
            this.enemies.add(enemy2);
        } else {
            System.out.println(nombreDeHerosVivant + " monstres vous attaquent !\nPréparez-vous à combattre");

            for(int i = 0; i < nombreDeHerosVivant; ++i) {
                enemy = new BasicEnemy();
                this.enemies.add(enemy);
            }
        }

        Iterator var8 = this.enemies.iterator();

        while(var8.hasNext()) {
            Enemy enemy1 = (Enemy)var8.next();
            System.out.println("Un " + enemy1.name + " apparaît");
        }

    }
}

