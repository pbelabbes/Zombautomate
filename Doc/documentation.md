# Documentation Zombautomate


### Entitées 

- Vie
- idEquipe
- Force 
- case ( ou position )
- automate

### Personnages:
- Force de base
- Vie 
- Exp
- Liste objets
- (Vitesse)
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
| Se battre   | pomme             | Joueurs, Zombies		   |
| Ramasser    | poire             | armes, nourriture -> herbe |
| Echanger    | batte de baseball | armes, nourriture          |
| Voler       | Katana 			  | armes, nourriture          | 
| Planter     | pousse 			  | herbe -> pousse            |
| Arroser     | lapin 			  | pousse -> arbre            | 
| Deposer     | Forêt 			  | herbe -> rocher            |

### Décors

- Rocher
- Herbe
- Lapin
- Pomme 
- Poire
- Forêt
- Batte de Baseball
- Katana

### Donner un coup:
- Zombie si perso case adjascente
- Perso (selon automate)

### Se déplacer:
- (Changer orientation) -> voir affichage
- Avancer

### Ramasser:
- Prendre objet (supprimer case)
- Mettre dans liste

### Echanger:
- Partager équitablement la reserve de nourriture entre 2 alliés
- Si personne ou ennemi -> Rien

### Voler:
- Prendre aléatoirement un objet de la liste (arme/nourriture) de son ennemi
- Si ami ou personne -> Rien

### Planter:
- Planter une graine qui deviendra de la nourriture



