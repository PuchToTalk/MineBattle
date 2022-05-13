package com.isep;

import com.isep.rpg.Game;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @auteur  (Paul)
 * @version (v.o1 - 13/05/2022)
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ControllerViews/views/MiniRPGLite.fxml"));
        Scene scene = new Scene(root, 899,583);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

// On lance la session du jeu via la fonction
