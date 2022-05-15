package com.isep;

import com.isep.rpg.Game;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
// On lance la session du jeu via la fonction playgame de la classe Game