package com.isep.rpg;

import java.util.Scanner;
import java.util.*;


public class Game {
    private List <Hero> heroes;
    private int playerTurn;
    /*    Scanner clavier = new Scanner(System.in);
        System.out.print("Donnez l'heure de début de la location (un entier) : ");
        int debut = clavier.nextInt();*/

    public Game(List<Hero> heroes, int playerTurn){
        this.playerTurn = playerTurn;
        this.heroes = heroes;
    }

    public static void playGame(){

    }
    public static void generateCombat(){
    }



}
