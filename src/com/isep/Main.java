package com.isep;

import com.isep.rpg.Game;



public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
// On lance la session du jeu via la fonction playgame de la classe Game