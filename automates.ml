(*
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list
*)


(*Il faut compiler la suite pour faire fonctionner cet exemple *)
let (automate_exemple: automate) = 
  [(1, ScanLoin(Pomme,N), Deplacer, N, 2,1) ;
   (1, ScanProche(Pomme,N),Ramasser,N,2,1);
   (1, ScanLoin(Ennemi,E),Deplacer,O,4,1);
   (4, ScanLoin(Katana,S),Deplacer,S,5,1);
   (5, ScanProche(Katana,E),Ramasser,E,6,1);
   (6, ScanProche(Ennemi,N),Attaquer,N,6,1);
   (3, ScanProche(Ennemi,E),Deplacer,O,3,1);
  ];;

let (automate_zombie: automate) = 
  [(1, ScanProche(Ennemi,N),Attaquer,N,1,1) ;
   (1, ScanProche(Ennemi,S),Attaquer,N,1,1);
   (1, ScanProche(Ennemi,E),Attaquer,O,1,1);
   (1, ScanProche(Ennemi,O),Attaquer,S,1,1);
   (1, ScanLoin(Ennemi,N),Deplacer,N,1,1);
   (1, ScanLoin(Ennemi,S),Deplacer,S,1,1);
   (1, ScanLoin(Ennemi,E),Deplacer,E,1,1);
   (1, ScanLoin(Ennemi,O),Deplacer,O,1,1);
  ];;
(*Mettre une priorité sur le ScanProche*)


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
type retour = Dir of direction | Nbr of int
(*Direction of direction | Nombre of int *)

(*
type decor =
|Pomme
|Rocher
|Lapin
|Batte_baseball
|Katana
|Arme
|Arbre
|Pousse
  *)

(* type personnage = Allie | Ennemi | Zombie *)

type cible = 
|Pomme
|Rocher
|Lapin
|Batte_baseball
|Katana
|Arme
|Arbre
|Pousse
|Allie
|Ennemi
|Zombie


type condition =
|ScanLoin of cible*retour (*désigne une fonction qui retourne la direction de l'élément recherché(la cible) le plus proche à une portée donnée. Si aucun élément recherché n'est présent, retourne 0*)
|ScanProche of cible*retour (*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)
|Et of condition*condition
|Present of cible*direction




type etat = int
type priorite = int
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list







let (scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun direction -> (src, ScanLoin(cbl,direction), act, direction , tgt, pri) ) [N;S;E;O];;

let (scan_proche_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun direction -> (src, ScanProche(cbl,direction), act, direction , tgt, pri) ) [N;S;E;O];;
