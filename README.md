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

* On lit la valeur de la case sur laquelle on se trouve et son environnement
* On effectue l'action représenté par son automate 
* On change d'état en fonction de l'action effectuée
* Se déplacer si besoin et recommencer

### Transitions-Automates-Représentation par un tableau

2 solutions:
* nombre de conditions fixé 
* nombre de conditions calculées 
On affecte à chaque transition donnée par le joueur un entier. Ainsi si le joueur dans son automate défini 15 conditions, le tableau resultant de celui-ci aura 15 lignes. Pour éviter d'avoir un trop grand nombre de condition oon peut définir un maximum même si on a déjà défini un "crédit de joueur" c'est à dire un certain nombre de cases qu'il lui est possible d'utiliser pour l'ensemble de ses joueurs.

## Contributors

Pierre BELABBES
Alexandre FERRERA
Douria ZENNOUCHE
Nicolas Homberg
Alice Rivoal
Skander KHALDI

