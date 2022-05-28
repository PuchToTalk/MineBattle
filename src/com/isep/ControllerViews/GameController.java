package com.isep.ControllerViews;
/**
 * @auteur(s)  (Paul)
 * @version (v.w1 - 26/05/2022)
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

/** Attributs updated : bordure vbox, toutes les model de cases qu'on va utiliser
 * VBox : essentiellement car mettre en évidence camps HERO // ENEMY
 * ajout de BorderPane
 * MsgText : pour afficher en textuel le message de l'action réalisé
 * **/

public VBox heroContainer; //Attribut contenant données du Hero (fenêtre gauche)
    public VBox enemiesContainer; //Attribut contenant données du Enemy (fenêtre droite)
    public BorderPane screenContainer;  //Bordure = la Border Box qui contient élémts du Sidebar
    public Text messageText; // Attribut qui affiche message de l'action choisi (ex : " attaque réussi / flèche bien ajouté etc.."
    private Game game; //Attribut "game" contenant l'ens de la GameLogic car s'agit d'une instance de la Game
    private int heroNumber = 0; // Attribut qui s'active lors du choix de récompenses puis disparaît après  choix des récompenses



 // 1ere étape : initialisation fait appel à l'apparition des boutons
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGame();
        addGameStartButtons();
    }


    /**
     *
     *            Grâce à "TextInputDialog" : possibilité d'afficher popup avec comme paramètres :
     *            Txt @contenu : le texte qu'on mettra (du type "combien de joueurs ? ") => utiliser le même principe pour ricochet
     *                  @titre : Mini rpg lite
     */

    private String ask(String txt) {
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(txt);
        tid.setGraphic(null);
        tid.setTitle("Mini RPG Lite");
        tid.showAndWait();
        return tid.getResult();
    }


    /**
     * Faisant appel à ask(txt)
     * > on obtient le nombre total de héro via le "parseInt" pour chaque classe
     */


    private void setGame() {
        int totalHeroes = Integer.parseInt(ask("Sasir Nombre total de Hero(s)"));
        int mage = Integer.parseInt(ask("Nombre de Mage(s)"));
        int hunter = Integer.parseInt(ask("Nombre de Hunter(s)"));
        int warrior = Integer.parseInt(ask("Nombre de Warrior(s)"));
        int healer = Integer.parseInt(ask("Nombre de Healer(s)"));
        game = new Game(totalHeroes, mage, healer, hunter, warrior); // lancement du jeu avec le nombre que l'user aura choisi préalablement avt début du jeu
    }


    /**
     * Nom à afficher sur bouton
     * path, source de l'image recherché depuis mon répertoire pour l'image du background
     *
     * Sortie : Bouton "btn"
     */

    private Button getButton(String name, String path) {
        Button btn = new Button(name);
        btn.setPrefSize(200, 200);

        return btn;
    }


    /**
     * @auteur(s)  (Paul)
     * @version (v.w2 - 27/05/2022)
     * Interface (in progress)
     * Scene Builders (cf doc.fxml) : ok <=> equivaut au croquis de départ avec 2 camps </=>
     * Mettre en place actions dynamiques sur BOUTONS
     * Msg à afficher de l'action réalisé
     * Attribuer actions sur chaque boutons
     * Boutons (aligné)
     * **/




    /**
     *  Affiche en Message textuel, quelle fonction / action du héros a été effectuée
     */

    private void setMessageText(String txt) {
        messageText.setText(txt);
    }
}


    /**
     * Applique la fonction employé correspondant au bouton appuyé
     * Utilisation d'un objet de Action Event (eventHandlers) pour connaître quelle case/ bouton a été sélectionné par l'user
     */

    private void actionPerformed(ActionEvent e) {
        // à compléter

        if (((Button) e.getSource()).getText().equalsIgnoreCase("Attaque")) {
            setMessageText(game.attack());
        } else if (((Button) e.getSource()).getText().equalsIgnoreCase("Defense")) {
            setMessageText(game.defend());
        } else if (((Button) e.getSource()).getText().equalsIgnoreCase("Utilise food")) {
            setMessageText(game.consumeFood());
        } else if (((Button) e.getSource()).getText().equalsIgnoreCase("Utilise potion")) {
            setMessageText(game.consumePotion());
        }
        try {
        } catch (Exception ignored) {
            // erreur à prédire : à compléter
        }
    }



    /**
     * Place les fcts attack(), defend(), useConsumable() (enfin food ou lembas) dans un button spécifique;
     * Initialise + Attribue action listener
     * l'ajoute puis afffiche sur l'écran du box milieu
     */

    private void addGameStartButtons() {

        Button btn = getButton("Attaque", "game/ControllerViews/images/sword.jpg");
        Button btn1 = getButton("Defense", "game/ControllerViews/images/shield.jpg");
        Button btn2 = getButton("Utilise food", "game/ControllerViews/images/gapple.jpg");
        Button btn3 = getButton("Utilise potion", "game/ControllerViews/images/potion.jpg");

        TilePane tp = new TilePane();
        tp.setAlignment(Pos.CENTER);
        tp.getChildren().addAll(btn, btn1, btn2, btn3);
        screenContainer.setCenter(tp);
        tp.setHgap(10);
        tp.setVgap(10);

        btn.setOnAction(this::actionPerformed);
        btn1.setOnAction(this::actionPerformed);
        btn2.setOnAction(this::actionPerformed);
        btn3.setOnAction(this::actionPerformed);
    }

}
