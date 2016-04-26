# Projet Domaine Informatique recommandation avec LensKit


Ceci est un projet de démonstration utilisant [LensKit][]. Il génère une recommandation
de domaine informatique pour un utilisateur donné.

Ce code est basé sur `org.grouplens.lenskit.hello.HelloLenskit`. Il a été adpaté à notre
démarche.

Lenskit Home page et documentation : [LensKit home page][LensKit]

## Project Setup

Ce projet utilise [Gradle][gradle] pour la construction du projet et la gestion de la dépendance.
L'importation est simple dans un IDE ; Gradle est inclus ou disponible sur NetBeans , IntelliJ IDEA
et Eclipse . Ces IDEs importeront directement votre projet du fichier `build.gradle` et mettront en place
la construction et les dépendances .

Le fichier `build.gradle` contient la définition du projet et de ses dépendances .

## Building and Running

Pour lancer le projet, spécifiez l'algorithme que vous souhaitez utiliser parmi :
    * user pour User User collaborative filtering
    * item pour Item Item collaborative filtering
    * matrix pour Matrix Factorization
    * slope pour Slope-One
Puis, le numéro de l'utilisateur pour lequel vous voulez générer une recommendation
