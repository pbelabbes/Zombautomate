# Contrat établi

## Règles 

* Nombre de joueurs : 1 à 2 
* Joueurs 1 et 2 : Contôle les suirvivants
* Chaque joueur possède un groupe de survivants allant de 1 à 10
* Chaque survivant est lié à un automate qui régit son comportement
* Ces automates ont un coût. Plus un automate est complexe, plus il coûte cher.
* Un joueur a un nombre de crédits qui lui permettent de créer des personnages qui obéïssent à un automate codé en Ocaml.
* Les zombies sont programmés à l'avance, sont générés aléatoirement par le programme et leur automate n'est pas sur la carte.

## Actions possibles

*Toutes les actions sont réalisées sur une case adjacente et dans une direction Nord, Sud, Est ou Ouest*

* Se déplacer : Le personnage se déplace sur une case adjacente
* Attaquer : Le personnage attaque un autre personnage ou casse un objet de type arbre ou rocher
* Rammasser : Le personnage ramasse un objet ( Nourriture ou Arme )
* Planter : Le personnage plante une graine sur de l'herbe qui devient une pousse grâce aux graines récoltées
* Arroser : Le personnage arrose une pousse qui devient un arbre 
* Déposer : Le personnage dépose un rocher grâce aux cailloux qu'il aura récoltés
* Voler : Le personnage vole une arme, de la nourriture, des cailloux ou des graines
* Echanger : Le personnage effectue un échange d'armes avec un allié adjacent

## Fonctionnalités complémentaires 

* Rendre la création d'automate moins fastidieuse
  * Gestion de conditions imbriquées
  * Fonctions complexes de recherche de direction
* Mise en place d'un menu interactif pour que les joueurs puissent créer leur équipe
* Mettre en évidence le personnage dont l'automate vient d'être modifié
* Mettre en évidence l'automate du personnage qui est entrain de jouer

# Contrat optionnel

* Ajout d'un système de sauvegarde de profil joueur avec ses automates, son expérience et un historique de ses parties
* Faire en sorte que les zombies attaquent en horde
* Faire en sorte que les zombies soient controlables
