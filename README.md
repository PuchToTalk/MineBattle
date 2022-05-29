# TP4_progress

13/02/22
> brouillons
> mini jeu test le programme


23/03/22
> Reprise depuis le début
> Redéfinition des classes
> Reprise de la structure du diagramme UML



30/03/22 
Remodification du jeu
> simplification de l'intéraction text
> Début de l'aventure correct
> fonctionnement du programme 
=> Jeu fonctionnel v1 (affichage simple + sélection héro)

31/03/22
Continuité de la progression d'hier
> restructuration des intéractions depuis la sélection héro
> Quelques tests unitaires de combat : début de generateCombat + SimulationCombat
=> Jeu fonctionnel v2 (test unitaires début de chaque classes + simulations combat)


01/04/22
Compréhension + application de la notion de builder
> Restitution des notions
> Simplification du programme pour + de performance
> Réduction des fonctions
> Finalisation + optimisation des tests unitaires sur les classes héros


03/04/22
Continuité : l'intéraction "Choix du héro"
> Mise en place du système de battle (in progress)
> Système de récompense (pas encore)
> Optimisation du code coverage (en général + début text)

09/04/22
Avancement intéraction Combat battle mais pb
> N'arrive pas à combattre adversaire autre que la position du héro (i)
> boucle for pour simuler le combat
> Blocage sur le principe système battle à rotation


17/04/22
Grande pause
> Fin du système de Battle (choix d'adversaire besoin de corrections)
> Choix récompenses déjà commencé


22/04/22
Reprise en main
> Fonction attaquer fonctionnel
> Tests unitaires correct
> Correction du système de battle
> Avancement choix récompenses


27/04/22
Finalisation système battle
> Fin correction apport pour choix de l'adversaire
> Maj de l'affichage pour + agréable avec fonction printSeparator() / clearConsole()
> fonction Defendre terminé
> Avancement choix récompenses (armures, potions à faire)


06/05/22
Optimisation des différentes branches de la GameLogic avant implémentation
> partie Enemy : Basic Enemy / Boss
> partie Hero : Warrior / SpellCaster / Hunter
> partie Consummable : Food / Potion


13/05/22
Continuité gameLogic 95 % + texte de remplacemenet cf tuto youtube
> Same branches



14/05/22
GameLogic terminé 99 %
> vérification & tests unitaires établies pour chaque classe avec fct attack() / défense()


15/05/22
Optimisation de chaque classes
> Simplficiation de la classe Game (Pour la GameLogic)
> Au lieu d'avoir system.print.ln le déroulement <-> implémenter dans une fonction 
> exemple : Réécriture de la fonction attaque / défense / food / potions (dans la switch)



> Optimisation choix de Récompenses
> Simplification de la classe Game
> Au lieu d'avoir texte imprimé, on les met dans des fonctions pour automatiser & simplifier l'appel de ces functions
> ex : Réécriture pour les cas du switch lorsque l'user choisit sa récompense 1/2/3/4/5/6
(function increaseWP, incresaseArmor, increaseEfficiency, increaseNumber, increaseArrows, decreaseManaCost


16/05/22
Ajout de Builders functions pour éviter répititions inutiles dans les println
> Préparation de functions rapide pour les loadHeroes & lancement du fight (GenerateCombat)
> Préparation de Builders pour setup les héros (WARRIORS + HUNTERS)


Same idée à faire pour les ennemis après

20/05/22
Builders sur partie Enemy + Basic enemy + Boss
> Préparation de functions rapide pour setup les paramètres + stats


22/05/22
Optimisation (5) des constructeurs POST choix awards
> Enesemble des parties Héros
> Ensemble des parties Enemy


23/05/22
Optimisation (6) des constructeurs POST choix awards
> Enesemble des parties secondaires (Food & Potion & spécifications Choix Awards)


24/05/22
Fonction InputParser à vérif
> dernières modifs pour la GameLogic


25/05/22
Début Interface
> fenêtre
> boutons
> modélisation interface grâce Scene Builders (fichier xml en cours)
> voir croquis de mes attentes


27/05/22
Correction des petits détails
Tests Unitaires correction
> tests corrects pour les 4 classes

28/05/22
Continuation Interface
> SceneBuilders : répartition des éléments et validation visuel du croquis
> Savoir quoi utiliser : Vbox, Containers, boutons, popup


29/05/22
Interface rush final
> Ajout Cases pour les deux camps HERO / ENEMY
> Ajout couleurs sur les cases pour montrer statut ALIVE / DEAD 
> Boutons dynamiques (actions du héros)
> Suite de l'histoire (choix des récompenses : via boutons dynamiques)
> DoContinue() : relancer la session
> Ajout image en arrière plan des boutons
> Ajout ombre sur cases
