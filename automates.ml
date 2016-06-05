(*
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list
*)
let automate_exemple = 
  [(1, ScanLoin(Pomme,N), Deplacer, N, 2,1) ;
   (1,ScanProche(Pomme,N),Ramasser,N,2,1);]


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
|ScanLoin of cible*retour (*désigne une fonction qui retourne la direction de l'élément recherché(la cible) le plus proche à une portée donnée. Si aucun élément recherché n'est présent, retourne 0*)
|ScanProche of cible*retour (*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)

type etat = int
type priorite = int
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list

let (scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun direction -> (src, ScanLoin(cbl,Direction(direction)), act, direction , tgt, pri) ) [N;S;E;O];;

let (scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun direction -> (src, ScanLoin(cbl,Direction(direction)), act, direction , tgt, pri) ) [N;S;E;O];;
