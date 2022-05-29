package com.isep.ControllerViews;
/**
 * @auteur(s)  (Paul)
 * @version (v.w1 - 26/05/2022)
 * Début Interface
 * Doc FXML quasi OK (cf Scene Builders + croquis)
 * **/


/** Import l'ensemble des classes contenus dans le cadre du fctment du jeu
 * Import surtout de la GameLogic
 * Et les classes des héros/ ennemis
 * **/
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

//            INITIALISATION                 //

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

    //            GAME SCREEN / TITLE / BORDER              //
    /**
     *
     *   Grâce à "TextInputDialog" : possibilité d'afficher popup avec comme paramètres :
     *   Txt @contenu : le texte qu'on mettra (du type "combien de joueurs ? ") => utiliser le même principe pour ricochet
     *   @titre : Mini rpg lite
     */

    private String ask(String txt) {
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(txt);
        tid.setGraphic(null);
        tid.setTitle("Mini RPG Lite");
        tid.showAndWait();
        return tid.getResult();
    }

    //            INITIALISATION DEBUT JEU (NBRE HEROS)                //

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
     * @auteur(s)  (Paul)
     * @version (v.w2 - 27/05/2022)
     * Interface (in progress)
     * Scene Builders (cf doc.fxml) : ok <=> equivaut au croquis de départ avec 2 camps </=>
     * Mettre en place actions dynamiques sur BOUTONS
     * Msg à afficher de l'action réalisé
     * Attribuer actions sur chaque boutons
     * Boutons (aligné)
     * **/

    //            AFFICHAGE SUR CONSOLE ACTION                //


    /**
     *  Affiche en Message textuel, quelle fonction / action du héros a été effectuée
     */

    private void setMessageText(String txt) {

        messageText.setText(txt);
    }
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

    //            INITIALISATION, AFFICHAGE PARAMTRES                //


    /**
     * Le Label des barres latéraux gauche / droite pour exactement indiquer le camps ""Heroes / Enemies""
     */
    private void initializeLabel() {
        Label label = new Label("Heros");
        label.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: white;");
        heroContainer.getChildren().add(label);
        label = new Label("Ennemis");
        label.setStyle("-fx-font-size: 20;" +
                "-fx-text-fill: white;");
        enemiesContainer.getChildren().add(label);
    }

    /**
     * Insère : String textuelles : du type Life / ManaPoints / etc..
     * Insère :  Informations stats du héro / enemy
     * En sortie : Affiche Paramètres de la case (ou ligne horizontale) du type  " LifePoints : 10 "
     */

    private HBox getHorizontalBox(String txt, String inf) {
        HBox hb = new HBox();
        hb.setPadding(new Insets(5));
        hb.getChildren().addAll(new Text(txt), new Text(inf));
        return hb;
    }




    //            RECUPERATION INFOS STATS SUR USERS (Hero / Enemy)                 //



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
     * Même idée que précédemment mais prend en entrée une instance de Hero
     * Retournant les mêmes catégories + d'autres en infos en suppléments (comme Armure / Qtité Food  / potion /flèches )
     */
    private VBox getHeroInformationPane(Hero hero) {
        VBox vb = new VBox();
        vb.setPrefWidth(139);
        vb.setMaxWidth(139);
        vb.setStyle("-fx-background-color: #ffff;");
        vb.getChildren().add(getHorizontalBox("Nom:   ", hero.getName()));
        vb.getChildren().add(getHorizontalBox("Vie:       ", "" + hero.getLifePoints()));
        vb.getChildren().add(getHorizontalBox("Armure:      ", "" + hero.getArmor()));
        vb.getChildren().add(getHorizontalBox("Degat:      ", "" + hero.getWeaponDamage()));
        vb.getChildren().add(getHorizontalBox("Qtité Food:   ", "" + hero.getLembas().size()));
        vb.setEffect(new DropShadow(10, Color.WHITE));
        if (hero instanceof Hunter) {
            vb.getChildren().add(getHorizontalBox("Qtité Fleches: ", "" + hero.getArrows()));
        }
        if (hero instanceof Mage || hero instanceof Healer) {
            vb.getChildren().add((getHorizontalBox("Qtité Potion:        ", "" + hero.getPotions().size())));
            vb.getChildren().add((getHorizontalBox("Point Mana:        ", "" + hero.getManaPoints())));
            vb.getChildren().add(getHorizontalBox("Coût Mana:     ", "" + hero.getManaCost()));
        }
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


    /**
     * Même disposition que pour la catégorie Enemy : on les place de mm style que pour case enemy
     * + Insère couleur propre au statut VIE/MORT des persos (si en vi : ajout de case + couleur vive / sinon si vie < 0 : modéliser case + couleur rouge/ sinon applique l'ajout d infos)
     */

    private void setHeroContainer() {
        for (Hero hero : game.getHeroList()) {
            if (game.getHeroList().get(game.getPlayerTurn()) == hero) {
                VBox vb = getHeroInformationPane(hero);
                vb.setStyle("-fx-background-color: #ff0087;");
                heroContainer.getChildren().add(vb);
            } else if (hero.getLifePoints() <= 0) {
                VBox vb = getHeroInformationPane(hero);
                vb.setStyle("-fx-background-color: #ff0101;");
                heroContainer.getChildren().add(vb);
            } else {
                heroContainer.getChildren().add(getHeroInformationPane(hero));
            }
        }
    }


    //            GAME RULE AFFICHAGE SCREEN                 //


    /**
     * Intervient quand cas du WIN / LOOSE
     * Affiche un pop-up / alerte
     */

    private void alertDialogue(String txt) {
        System.out.println("Interaction");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, txt);
        alert.showAndWait();
    }

    /**
     * MAJ des stats & du "containers"
     * Fonction fondamentale car on fait appel à chaque tour (maj des infos à chaque tour)
     */

    private void refreshNode() {
        heroContainer.getChildren().clear();
        enemiesContainer.getChildren().clear();
        try {
            initializeLabel();
            setHeroContainer();
            setEnemiesContainer();
        } catch (Exception ignored) {

        }
    }


    /**
     * Si "WIN" : on affiche popup de victoire / Sinon, "LOOSE" : affiche popup de game over
     * Si win(), on active heroesChoice() <-> choix des récompenses </->
     * Sinon demande, de recommencer ou non : doContinue()
     */

    private void winLoose() {
        if (game.win()) {
            System.out.println("Encore");
            alertDialogue("VICTOIRE!!!");
            // Lance la suite avec choix des récompenses
            heroesChoice();
            setEnemiesContainer();
        } else if (game.lose()) {
            alertDialogue("Game Over, vous avez perdu..");
            // possibilité de relancer le jeu
            doContinue();


            initialize(null, null);
        }
    }



    /**
     * Fonction demande par popup si on souhaite continuer/recommencer le combat ou non
     * Oui : ReLoad l'ens des étapes : game.NewGame()
     * Non : Sort du jeu
     */

    private void doContinue() {
        System.out.println("Continuer");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Continuer ?");

        // si oui continuer
        ButtonType buttonTypeOne = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        // sinon, arrêter le jeu "exit"
        ButtonType buttonTypeTwo = new ButtonType("Non", ButtonBar.ButtonData.NO);


        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        // les cas :
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            alertDialogue(game.newCombat());
        } else if (result.get() == buttonTypeTwo) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }


    //            SET UP DES BOUTONS DYNAMIQUES / ACTIONS DU USER               //


    /**
     * Nom à afficher sur bouton
     * path, source de l'image recherché depuis mon répertoire pour l'image du background
     *
     * Sortie : Bouton dynamique "btn"
     */

    private Button getButton(String name, String path) {
        Button btn = new Button(name);
        btn.setPrefSize(200, 200);

        return btn;
    }



    /**
     * Applique la fonction employé correspondant au bouton appuyé
     * Utilisation d'un objet de Action Event (eventHandlers) pour connaître quelle case/ bouton a été sélectionné par l'user
     */

    private void actionPerformed(ActionEvent e) {
        refreshNode();
        winLoose();
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
            winLoose();
            refreshNode();
        } catch (Exception ignored) {
        }
    }



    /**
     * Place les fcts attack(), defend(), useConsumable() (enfin food ou lembas) dans un button spécifique;
     * Initialise + Attribue action listener
     * l'ajoute puis afffiche sur l'écran du box milieu
     */

    private void addGameStartButtons() {

        Button btn = getButton("Attaque", "com.isep/ControllerViews/images/sword.jpg");
        Button btn1 = getButton("Defense", "com.isep/ControllerViews/images/shield.jpg");
        Button btn2 = getButton("Utilise food", "com.isep/ControllerViews/images/gapple.jpg");
        Button btn3 = getButton("Utilise potion", "com.isep/ControllerViews/images/potion.jpg");

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


    //            CHOIX RECOMPENSES : Boutons                //


    /**
     * Exécute le choix précédent de la récompense
     * Utilise objet Action Event (eventHandlers) pour reconnaître le contenu du bouton
     */

    private void choiceAction(ActionEvent e) {
        refreshNode();
        Button btn = (Button) e.getSource();
        if (btn.getText().contains("Armure")) {
            setMessageText(game.increaseArmor());
        } else if (btn.getText().contains("Degat")) {
            setMessageText(game.increaseWeaponDamage());
        } else if (btn.getText().contains("Coût Mana")) {
            setMessageText(game.decreaseManaCost());
        } else if (btn.getText().contains("Fleches")) {
            setMessageText(game.increaseArrows());
        } else if (btn.getText().contains("Efficacite")) {
            setMessageText(game.increaseFoodAndPotionEffectiveness());
        } else if (btn.getText().contains("Qtité")) {
            setMessageText(game.increasePotionAndFoodNumber());
        }


        // Rafraichis statut de l'équipe
        if (heroNumber >= game.getHeroList().size()) {
            System.out.println("");
            doContinue();
            setEnemiesContainer();
            addGameStartButtons();
            refreshNode();
            heroNumber = 0;
            return;
        }
    }


        /**
         * Fonction apparaît seulement si le héro WIN() le fight :
         * Utilisation de Action Listener (EventListeners) pour lire ce qui a été selectionné depuis boutons
         */

        private void heroesChoice() {
            System.out.println("Again");

            Button btn1 = new Button("Augmenter Armure");
            btn1.setPrefSize(400, 50);

            Button btn2 = new Button("Augmenter Degat");
            btn2.setPrefSize(400, 50);

            Button btn3 = new Button("Augmenter Efficacite Food & Potion");
            btn3.setPrefSize(400, 50);

            Button btn4 = new Button("Augmenter Qtite Food & Potion");
            btn4.setPrefSize(400, 50);

            Button btn5 = new Button("Augmenter Fleches");
            btn5.setPrefSize(400, 50);

            Button btn6 = new Button("Diminuer Coût Mana");
            btn6.setPrefSize(400, 50);

            FlowPane fp = new FlowPane();
            fp.setAlignment(Pos.CENTER);
            fp.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6);

            btn1.setOnAction(this::choiceAction);
            btn2.setOnAction(this::choiceAction);
            btn3.setOnAction(this::choiceAction);
            btn4.setOnAction(this::choiceAction);
            btn5.setOnAction(this::choiceAction);
            btn6.setOnAction(this::choiceAction);

            screenContainer.setCenter(fp);
        }





}













}
