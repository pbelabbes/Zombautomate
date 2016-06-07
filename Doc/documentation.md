# Documentation Zombautomate

## Contrat prévisionnel

### Présentation globale : 

* Nombre de Joueur : 1 à 2
* Joueur 1 et 2 : Controle les survivants
* Chaque joueur possède un groupe de survivants allant de 1 à 10.
* Ces survivants sont lié un un automate qui régit leurs comportement.
* Ces automates ont un coût. Plus un automate est complexe, plus il coûte chers. 
* Un joueur a un nombre de crédit qui lui permet de créer des personnages régient par un automate. 
* Ordi : génère les zombies a partir d’un automate programmé à l’avance.

### Actions : Toutes les actions sont réalisées sur une case adjacente et dans une direction Nord ,Sud ,Est ou Ouest.
* Se déplacer : Le personnage se déplace sur une case adjacente
* Attaquer : Le personnage attaque un autre personnage ou casse un objet de type arbre ou rocher.
* Ramasser : Le personnage ramasse un objet ( Nourriture ou Arme )
* Planter : Le personnage plante une graine sur de l’herbe qui devient une pousse gràce aux graines qu’il aura récolté
* Arroser : Le personnage arrose une pousse qui devient un arbre.
* Se Cacher : Le personnage se cache dans un arbre est devient invisible pour le zombie
* Déposer : Le personnage dépose un rocher grace aux cailloux qu’il aura récolté
* Voler : Le personnage vole une arme, de la nourriture, des cailloux ou des graines.

### Fonctions complémentaires :

* Gestion de conditions complexes. Implémentation de condition avec un “Et”.
* Fonction de simplification pour la création des automates ( AD - All direction )
* Présence des automates des joueurs sur la carte. Les joueurs ont une position initiale aléatoire sur la carte.
* Mise en place d’un menu intéractif
* Gestion de la création et de la lecture XML.

### Character 

- Vie
- Joueur
- Force 
- case ( ou position )
- automate

### Personnages:
- Force de base
- Vie 
- Exp
- Liste objets
- Position

### Zombies:
- Vie
- Force
- Position
- (Vitesse)

## Actions et Décors 

### Actions

| ACTION      | REPRESENTATION    | IMPACT                     |
| ----------- | :---------------: | :------------------------: |
| Se déplacer | herbe             | Déplacement N,S,E,O        |
| Se battre   | pomme             | Joueurs, Zombies	       |
| Ramasser    | rocher            | armes, nourriture -> herbe |
| Echanger    | batte de baseball | armes, nourriture          |
| Voler       | Katana 		  | armes, nourriture          | 
| Planter     | pousse 		  | herbe -> pousse            |
| Arroser     | lapin 		  | pousse -> arbre            | 
| Deposer     | Forêt 		  | herbe -> rocher            |

### Décors

- Rocher
- Herbe
- Lapin
- Pomme 
- Pousse
- Forêt
- Batte de Baseball
- Katana

### Attaquer:
- Zombie si perso case adjascente
- Perso (selon automate)
- Une attaque a plusieurs effets différents sur les decors
- Elle réduit les points de vie d'un character s'il se trouvait la
- Elle casse les rochers qui peuvent donner soit un katana soit un lapin
- Elle coupe les arbres pour en récolter des pommes ou pour faire une batte de baseball

### Se déplacer:
- Déplace un character dans la direction indiquée

### Ramasser:
- Prendre l'objet présent sur la case et changer son décor en herbe
- Si arme: la mettre dans liste du personnage
- Si nourriture: augmenter le stock de nourriture du joueur

### Deposer:
- Dépose une pierre sur de l'herbe ou ne fait rien

### Echanger:
- Partager équitablement la reserve de nourriture entre 2 alliés
- Si personne ou ennemi -> Rien

### Voler:
- Prendre aléatoirement un objet de la liste (arme/nourriture) de son ennemi
- Si ami ou personne -> Rien

### Planter:
- Planter une graine qui deviendra de la nourriture

### Arroser:
- Faire pousser une pousse en arbre ou rien

