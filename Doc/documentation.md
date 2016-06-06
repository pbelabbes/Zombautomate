# Documentation Zombautomate


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

