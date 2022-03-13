package com.isep.rpg;

import java.util.Scanner;

    /*    Scanner clavier = new Scanner(System.in);
        System.out.print("Insérer la valeur : ");
        int debut = clavier.nextInt();*/


import java.util.*;

// Pour les listes + random tirage aléatoire

public class Game {
    public List <Hero> heroes;
    public List<Enemy> enemies;
    public int playerTurn;
    public List<Potion> potions;
    public List<Food> lembas;





    public Game(List<Hero> heroes, int playerTurn){
        this.playerTurn = playerTurn;
        this.heroes = heroes;
    }

    public void List<Hero> listOfHeroes(String HeroClass) {
        switch ("HeroClass") {
            case "Warrior":
                heroes.add(new Warrior());
                return HeroClass;
            break;


            case "Healer":
                heroes.add(new Healer());
                return HeroClass;
            break;


            case "Hunter":
                heroes.add(new Hunter());
                return HeroClass;
            break;

            case "Mage":
                heroes.add(new Mage());
                return HeroClass;
            break;

            default:
                System.out.println(" Vous n'avez choisi aucun héro ou le héro correspondant n'est pas valide ");
                return HeroClass;
            break;

        }
    }
// on ajoute à chaque étage un boss : réf à la Sword Art Online
    public void List<Enemy> listOfEnemies() {
        int niveauBoss = Random.nextInt(100);
        switch (niveauBoss) {
            case 100:
                enemies.add(new Boss());
                return enemies;
            break;

            case 50:
                enemies.add(new Boss());
                return enemies;
            break;

            case 10:
                enemies.add(new Boss());
                return enemies;
            break;

            default:
                enemies.add(new BasicEnemy());
                return enemies;
            break;

        }
    }
        return enemies;




    Scanner scanner1 = new Scanner(System.in);
        System.out.println(" Jouer au jeu ? ");
    String startGame = scanner1.nextFloat();

    Scanner scanner2 = new Scanner(System.in);
        System.out.println(" Commencer les combat? ");
    String startFight = scanner2.nextFloat();


    Scanner scanner3 = new Scanner(System.in);
        System.out.println(" Que faire ? Attaquer/defendre/potion/ food ? ");
    String startAction= scanner3.nextFloat();




    public useConsumable(String startAction) {
        if (startAction == "potion") {
            potions.add(-1);
        } else if (startAction == "food") {
            lembas.add(-1);
        } else {
            System.out.println(" Commande incorrecte, veuillez réessayer ");
        }
        return potions;
        return lembas;

    }

    public usePotion(String startAction) {
        if (startAction == "potion") {
            potions.add(-1);
        } else {
            potions.add(0);
        }
        return potions;


    }
    public useFood(String startAction) {
        if (startAction == "food") {
            lembas.add(-1);
        } else {
            lembas.add(0);
        }
        return lembas;


    }



// démarrer le jeu

    public static void playGame(){

    }
    public static void generateCombat(){
    }



}
