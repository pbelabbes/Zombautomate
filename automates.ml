(*
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list
*)


(*Il faut compiler la suite pour faire fonctionner cet exemple *)
let (automate_exemple: automate) = 
  [(1, ScanLoin(Pomme,Dir(N)), Deplacer, N, 2,1) ;
   (1, ScanProche(Pomme,Dir(N)),Ramasser,N,2,1);
   (1, ScanLoin(Ennemi,Dir(E)),Deplacer,O,4,1);
   (4, ScanLoin(Katana,Dir(S)),Deplacer,S,5,1);
   (5, ScanProche(Katana,Dir(E)),Ramasser,E,6,1);
   (6, ScanProche(Ennemi,Dir(N)),Attaquer,N,6,1);
   (3, ScanProche(Ennemi,Dir(E)),Deplacer,O,3,1);
  ];;

(* let (scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri -> *)



(*Un automate de guerrier *)
let (automate_perso_1: automate) = 
  presence_AD 0 Katana Ramasser 1 1
  @
    presence_AD 0 Batte_baseball Ramasser 1 1
  @
    scan_loin_AD 0 Katana Deplacer 0 1
  @
    scan_loin_AD 0 Batte_baseball Deplacer 0 1
  @
    presence_AD 1 Ennemi Attaquer 1 1
  @
    scan_loin_AD 1 Ennemi Attaquer 1 1
  @
    scan_loin_AD 1 Batte_baseball Deplacer 0 1
  @
    scan_loin_AD 1 Katana Deplacer 0 1;;


(*Un automate de farmer *)
let (automate_perso_1: automate) =
   case_allie 0 Deplacer 0 1
  @
   presence_AD 1 Pomme Ramasser 0 1
  @
    presence_AD 1 Lapin Ramasser 0 1
  @
    scan_loin_AD 1 Pomme Deplacer 0 1
  @
    scan_loin_AD 1 Lapin Deplacer 0 1
  @
    presence_AD 1 Pousse Arroser 0 1
  @
    presence_AD 1 Herbe Planter 0 1
  @
    presence_AD 1 Zombie Deplacer 1 1
  @
    presence_AD 2 Zombie Deplacer 0 1
  @
    presence_AD 2 Arbre Cacher 0 1
  @ 
    scan_loin_AD 2 Arbre Deplacer 1 1
  @
    presence_AD 0 Ennemi Deplacer 1 1
  @
    presence_AD 2 Ennemi Voler 0 1 ;;


let (automate_zombie: automate) = 
  presence_AD 0 Ennemi Attaquer 0 1
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1;;

(*Mettre une priorité sur presence*)



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
|Herbe
  *)

(* type personnage = Allie | Ennemi | Zombie *)

type cible = 
|Pomme
|Rocher
|Lapin
|Batte_baseball
|Katana
|Arbre
|Pousse
|Allie
|Ennemi
|Zombie
|Herbe


type condition =
|ScanLoin of cible*retour (*désigne une fonction qui retourne la direction de l'élément recherché(la cible) le plus proche à une portée donnée. Si aucun élément recherché n'est présent, retourne 0*)
|ScanProche of cible*retour (*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)
|Et of condition*condition
|Present of cible*direction
|Case_allie
|Case_ennemi
|Case_neutre


type etat = int
type priorite = int
type transition = etat * condition * action * direction  * etat * priorite
type automate = transition list

type equipe = (automate*string) list  (* liste d'automates avec un nom de personnage associé *)


let (scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun direction -> (src, ScanLoin(cbl,Dir(direction)), act, direction , tgt, pri) ) [N;S;E;O];;

let (scan_proche_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map  (fun (direction:direction) -> (src, ScanProche(cbl,Dir(direction)), act, direction , tgt, pri) ) [N;S;E;O];;

let (presence_AD: etat -> cible -> action -> etat -> priorite -> automate) = fun src cbl act tgt pri ->
  List.map (fun (direction:direction) -> (src, Present(cbl,direction), act, direction, tgt, pri) ) [N ; S ; E ; O];;





(* Ecriture fichiers *)

   (* Creation des strings *)
let (etat_to_string : etat->string) = fun etat -> string_of_int etat;;

let (priorite_to_string : priorite->string) = fun priorite -> string_of_int priorite;;

let (direction_to_string: direction->string) = fun d -> match d with
  |N -> "N"
  |E -> "E"
  |S -> "S"
  |O -> "O";;

let (retour_to_string: retour->string) = fun r -> match r with
  |Dir(d)-> direction_to_string d
  |Nbr(i)-> string_of_int i;;

let (cible_to_string:  cible->string) = fun c -> match c with
  |Herbe -> "Herbe"
  |Pomme -> "Pomme"
  |Rocher -> "Rocher"
  |Lapin -> "Lapin"
  |Batte_baseball -> "Batte_baseball"
  |Katana -> "Katana"
  |Arbre -> "Arbre"
  |Pousse -> "Pousse"
  |Allie -> "Allie"
  |Ennemi -> "Ennemi"
  |Zombie -> "Zombie";;
 
let (action_to_string: action->string) = fun a -> match a with
|Deplacer->"Deplacer"
|Attaquer->"Attaquer"
|Voler->"Voler"
|Cacher->"Cacher"
|Ramasser->"Ramasser"
|Planter->"Planter"
|Arroser->"Arroser"
|Deposer->"Deposer";;

let rec (condition_to_string: condition->string) = fun c -> match c with 
  |ScanLoin(cbl,ret)->"ScanLoin("^ cible_to_string cbl ^","^ retour_to_string ret ^")"
  |ScanProche(cbl,ret)->"ScanProche(" ^ cible_to_string cbl ^","^ retour_to_string ret ^")"
  |Present(cbl,dir)->"Present(" ^ cible_to_string cbl ^","^ direction_to_string dir ^")"
  |Et(c1,c2)->"Et(" ^ condition_to_string c1 ^","^ condition_to_string c2 ^")";;

   (* ecriture dans un fichier *)

let (print_etat_courant: out_channel->etat->unit) = fun fic courant ->
  output_string fic ("\n\t\t\t<etat_courant>"^etat_to_string courant ^"</etat_courant>");;

let (print_condition: out_channel->condition->unit) = fun fic condition ->
  output_string fic ("\n\t\t\t<condition>"^condition_to_string condition ^"</condition>");;

let (print_action: out_channel->action->unit) = fun fic action ->
  output_string fic ("\n\t\t\t<action>"^action_to_string action ^"</action>");;

let (print_direction: out_channel->direction->unit) = fun fic direction ->
  output_string fic ("\n\t\t\t<direction>"^direction_to_string direction ^"</direction>");;

let (print_priorite: out_channel->priorite->unit) = fun fic priorite ->
  output_string fic ("\n\t\t\t<priorite>"^priorite_to_string priorite ^"</priorite>");;

let (print_etat_futur: out_channel->etat->unit) = fun fic futur ->
  output_string fic ("\n\t\t\t<etat_futur>"^etat_to_string futur ^"</etat_futur>");;


let (print_entete: out_channel->string->string->unit) = fun fic version encodage ->
  output_string fic ("<?xml version=\""^version^"\" encoding=\""^ encodage ^"\"?>");;

let (print_transition: out_channel->transition->unit) = fun fic trans -> let (courant,condition,action,direction,priorite,futur) = trans in print_etat_courant fic courant ; print_condition fic condition ; print_action fic action ; print_direction fic direction ; print_priorite fic priorite ; print_etat_futur fic futur;;

let rec (print_automate: out_channel->automate->unit) = fun fic aut-> match aut with
  |trans::reste-> output_string fic "\n\t\t<transition>" ; print_transition fic trans ; output_string fic "\n\t\t</transition>" ; print_automate fic reste
  |[]-> ()
  |_->failwith "erreur dans print_automate";;


let (print_personnage: out_channel->automate*string->unit) = fun fic automate -> let (aut,nom) = automate in
									       output_string fic ("\n\t<automate id =\""^nom^"\">") ; print_automate fic aut ; output_string fic "\n\t</automate>" ;;


let rec (print_equipe: out_channel->equipe->unit) = fun fic equipe -> match equipe with
  |aut::reste -> output_string fic "\n<persos>" ; print_personnage fic aut ; print_equipe fic reste
  |[]->output_string fic "\n</persos>"
  |_->failwith "souci dans print_equipe"




(*exemple *)
let fic = open_out "personnages_générés.xml";;
print_personnage fic (automate_perso_1,"guerier");;
close_out fic;;
