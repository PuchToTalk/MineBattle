package com.isep.ControllerViews;
/**
 * @auteur(s)  (Paul)
 * @version (v.w1 - 25/05/2022)
 * Début Interface
 * Doc FXML quasi OK (cf Scene Builders + croquis)
 * **/


/** Import l'ensemble des classes contenues dans le cadre du fctment du jeu **/
import com.isep.rpg.*;

/** BIBLIOTHEEEEQUEEEE DE L'INTERFAAACEEE JAVAAAFIKSSSSSSS (à utiliser ou bib utile) **/

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

/** Attributs **/
    public VBox heroContainer;
    public VBox enemiesContainer;
    private Game game;



 // 1ere étape : initialisation fait appel à l'apparition des boutons
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGame();
        addGameStartButtons();
    }


    // fct popup setup
    private String ask(String txt) {
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(txt);
        tid.setGraphic(null);
        tid.setTitle("Mini RPG Lite");
        tid.showAndWait();
        return tid.getResult();
    }

    // capte les ENTIERS qu'a soumis l'user lors du choix du "nombre de héro" de départ

    private void setGame() {
        int totalHeroes = Integer.parseInt(ask("Sasir Nombre total de Hero(s)"));
        int mage = Integer.parseInt(ask("Nombre de Mage(s)"));
        int hunter = Integer.parseInt(ask("Nombre de Hunter(s)"));
        int warrior = Integer.parseInt(ask("Nombre de Warrior(s)"));
        int healer = Integer.parseInt(ask("Nombre de Healer(s)"));
        game = new Game(totalHeroes, mage, healer, hunter, warrior); // lancement du jeu avec le nombre que l'user aura choisi préalablement avt début du jeu
    }


    // paramétrage d'un bouton

    private Button getButton(String name, String path) {
        Button btn = new Button(name);
        btn.setPrefSize(200, 200);

        return btn;
    }

    // nos 4 futurs boutons inch

    private void addGameStartButtons() {

        Button btn = getButton("Attaque");
        Button btn1 = getButton("Defense");
        Button btn2 = getButton("Utilise food");
        Button btn3 = getButton("Utilise potion");

    }


}
