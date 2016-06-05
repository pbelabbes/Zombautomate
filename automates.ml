(* LES TYPES *)

type direction = N (* nord *) | S | E | O

type action =
|Deplacer
|Attaquer
|Voler
|Cacher
|Ramasser
|Planter
|Arroser
|Deposer
(*
|Move
|Atk
|Steal
|Hide
|Pick
|Plant
|Spray
|Leave
*)
type retour = Direction of direction | Nombre of int


type decor =
|Pomme
|Rocher
|Lapin
|Batte_baseball
|Katana
|Arme
|Arbre
|Pousse

type personnage = Allie | Ennemi | Zombie

type cible = Decor of decor | Personnage of personnage

type condition =
|ScanDistant of cible*retour (*désigne une fonction qui retourne la direction de l'élément recherché(la cible) le plus proche à une portée donnée. Si aucun élément recherché n'est présent, retourne 0*)
|ScanProche of cible*retour (*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)
