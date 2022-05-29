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


    /**
     * @auteur(s)  (Paul)
     * @version (v.w3 - 28/05/2022)
     * Interface (in progress)
     *
     * Boutons dynamiques reliés aux actions du héros (4 boutons fonctionnels)
     * Affichage de l'Image en arrière-plan sur ces 4 boutons d'action (cf ControllerViews.images)
     * Box affichage Liste ennemis & héros + couleur en fct de leur statut
     *
     *
     * **/


    /**
     * Le Label des barres latéraux gauche / droite pour exactement indiquer le camps ""Heroes / Enemies""
     */
    private void initializeLabel() {

        label = new Label("Ennemis");
        label.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: white;");
        enemiesContainer.getChildren().add(label);
    }

    /**
     * Insère : String textuelles : du type Life / ManaPoints / etc..
     * Insère : Informations stats du héro
     * En sortie : Affiche une case (ou ligne horizontale) du type  " LifePoints : 10 "
     */

    private HBox getHorizontalBox(String txt, String inf) {
        HBox hb = new HBox();
        hb.setPadding(new Insets(5));
        hb.getChildren().addAll(new Text(txt), new Text(inf));
        return hb;
    }



    /**
     * Prend en paramètre e (désignant une instance de Enemy)
     * Retournant en sortie une case "VBox" grâce au lib JavaFX (avec les infos stats de l'ennemi choisi)
     */



    private VBox getEnemyInformationPane(Enemy e) {
        VBox vb = new VBox();
        vb.setPrefWidth(139);
        vb.setMaxWidth(139);
        vb.setStyle("-fx-background-color: #ffff;");
        vb.getChildren().add(getHorizontalBox("Nom   ", e.getName()));
        vb.getChildren().add(getHorizontalBox("Vie       ", "" + e.getLifePoints()));
        vb.getChildren().add(getHorizontalBox("Degat      ", "" + e.getDamage()));
        vb.setEffect(new DropShadow(10, Color.WHITE));
        return vb;
    }


    /**
     * Ajoute ENEMIES sur côté CONTAINER fenêtre à droite, un à un
     * Applique une couleur rouge si ENEMY = DEAD  i.e  EnemyLifepoint < 1
     * Statut de l'ennemi standard en rose
     */


    private void setEnemiesContainer() {
        for (Enemy enemy : game.getEnemyList()) {
            if (game.getEnemyByTurn() == enemy) { // met en évidence l'ennemi qui est ciblé
                VBox vb = getEnemyInformationPane(enemy);
                vb.setStyle("-fx-background-color: #ee00ff;");
                enemiesContainer.getChildren().add(vb);
            } else if (enemy.getLifePoints() <= 0) { // cas où ennemi est mort => l'applique une case fond rouge
                VBox vb = getEnemyInformationPane(enemy);
                vb.setStyle("-fx-background-color: #ff0000;");
                enemiesContainer.getChildren().add(vb);
            } else {
                VBox vb = getEnemyInformationPane(enemy);
                enemiesContainer.getChildren().add(vb);
            }
        }
    }













}
