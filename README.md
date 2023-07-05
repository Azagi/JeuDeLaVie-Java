Projet Java - Jeu de la Vie
Ce projet Java implémente le célèbre Jeu de la Vie, un automate cellulaire développé par John Conway. Le jeu se déroule sur une grille bidimensionnelle où chaque cellule peut être vivante ou morte. Les cellules évoluent en fonction d'un ensemble de règles simples.

Fonctionnalités implémentées
Classe Cellule : Cette classe représente une cellule du jeu de la vie. Elle possède un attribut etat qui indique si la cellule est vivante (true) ou morte (false). La classe fournit des méthodes pour obtenir et modifier l'état de la cellule.

Classe Grille : Cette classe représente la grille du jeu de la vie. Elle est composée d'un tableau bidimensionnel de cellules. Les principales fonctionnalités de la classe sont les suivantes :

Initialisation d'une grille avec une taille donnée.
Accès aux dimensions de la grille (tailleX, tailleY).
Accès aux cellules individuelles de la grille.
Création d'une grille à partir d'un fichier de configuration.
Affichage de la grille dans la console.
Comptage du nombre de voisins vivants d'une cellule donnée.

Interface AutomateCellulaire : Cette interface définit une méthode simulerGeneration() qui doit être implémentée par les automates cellulaires.

Classe JeuDeLaVie : Cette classe représente l'automate cellulaire du Jeu de la Vie. Elle implémente l'interface AutomateCellulaire et fournit une implémentation de la méthode simulerGeneration().
Simulation de l'évolution des cellules selon les règles du jeu de la vie.
Utilisation de threads pour améliorer les performances lors du calcul des nouvelles générations.

Classe AutomateUnidimensionnel : Cette classe représente un automate cellulaire unidimensionnel. Elle implémente l'interface AutomateCellulaire et fournit une implémentation de la méthode simulerGeneration(). 
Simulation de l'évolution des cellules unidimensionnelles avec les regles une cellule vivante reste vivante si elle a 1 ou 2 voisins vivant et une cellule morte devient vivante si elle a exactement 1 voisin vivant.

Classe Fourmi: Cette classe représente l'automate cellulaire de la fourmi de Langton. Elle implémente l'interface AutomateCellulaire et fournit une implémentation de la méthode simulerGeneration(). 

Choix techniques
Le patron de conception stratégie est utiliser pour ce projet, pour montre que le code pouvais être reutiliser pour d'autre automate cellulaire.
Une grille de Cellule pour faire evoluer un automate cellulaire avec des cellules soit morte soit vivante, est du au format des fichiers.


Compiler 
Pour compiler écrit dans le dossier du projet: javac *.java

Exécuter 
Pour excuter il y a deux version 
Version 1 affichage seulement sur le terminale avec la commande java MainT numDuAutomate fichier nbDEtape
0: pour l'Automate Unidimensionnel
1: pour Jeu De La Vie
2: pour la Fourmie de Langton
exemple: java MainT 1 clock-step0.life 4
execute le jeu de la vie sur la grille initial clock-step0.life et fait 4 generation

Version 2 affichage sur le terminale et une Interface avec la commande java Main numDuAutomate fichier 
0: pour l'Automate Unidimensionnel
1: pour Jeu De La Vie
2: pour la Fourmie de Langton
exemple: java Main 2 TestFourmi.txt  
execute la Fourmie de Langton sur le fichier TestFourmi.txt et pour passer à l'etape suivante il y a un bouton sur l'interface