
  type direction = N | S | E | O
  type action =
  |Deplacer
  |Attaquer
  |Voler
  |Echanger
  |Ramasser
  |Planter
  |Arroser
  |Deposer

  type retour = Dir of direction | Nbr of int

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
  |Ou of condition*condition
  |Present of cible*direction
  |Case_alliee of direction
  |Case_ennemie of direction
  |Case_neutre of direction
  |Defaut  (* Retourne toujours vrai. Permet de faire des actions par défaut *)
      
  type etat = int
  type priorite = int
  type transition = etat * condition * action * direction  * etat * priorite
  type automate = transition list    
  type equipe = (automate*string) list;;  (* liste d'automates avec un nom de personnage associé *)
    
  val scan_loin_AD: etat -> cible -> action -> etat -> priorite -> automate
    
  val scan_proche_AD: etat -> cible -> action -> etat -> priorite -> automate

  val presence_AD: etat -> cible -> action -> etat -> priorite -> automate
    
  val presence_oppose_AD: etat -> cible -> action -> etat -> priorite -> automate

  val scan_loin_oppose_AD: etat -> cible -> action -> etat -> priorite -> automate

  val make_xml: string->string->string->equipe->unit

