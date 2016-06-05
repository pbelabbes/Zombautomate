# Zombautomate
## But Du Jeu  

Le but du jeu est d'être le dernier survivant.

## Description 

Deux joueurs possèdent des survivants sur la carte et doivent rester en vie face à une invasion de zombies.
Le comportement de chaque joueur est décrit par un automate.
Cet automate est aussi un morceau de la map.
Chaque joueur influe sur les automates des autres joueurs.

## Fonctionnement 

* Le joueur définit un automate qui régit le comportement de ses survivants
* Avec l'automate on crée un tableau. Par exemple si on a 5 états on peut au maximum avoir 5 actions représentées par les transitions.
* Cet automate sera aussi un morceau du tableau. Les actions représentent donc aussi un décors sur la carte avec lequel on pourra interagir suivant l'action associé dans l'automate du joueur.


### Algorithme de base 

* On lit la valeur de la case sur laquelle on se trouve
* On effectue l'action représenté par son automate 
* On change d'état en fonction de l'action effectuée
* Se déplacer si besoin et recommencer


## Contributors

Pierre BELABBES
Alexandre FERRERA
Douria ZENNOUCHE
Nicolas Homberg
Alice Rivoal

