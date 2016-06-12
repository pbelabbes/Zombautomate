open Doc ;;

let (guerrier: automate) =
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
    scan_loin_AD 1 Ennemi Deplacer 1 1
  @
    scan_loin_AD 1 Batte_baseball Deplacer 0 1
  @
    scan_loin_AD 1 Katana Deplacer 0 1;;

(*guerrier : 2 états chacun 16 transitions *)

let (farmer: automate) = 
  ((0, Present(Lapin,N), Deplacer,N, 0, 1)::[(0, Et(Case_ennemie(N),Ou(Present(Lapin,N),Present(Pomme,N))), Deplacer, N, 0, 1)])
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
    scan_loin_AD 2 Arbre Deplacer 1 1
  @
    presence_AD 0 Ennemi Deplacer 1 1
  @
    presence_AD 2 Ennemi Voler 0 1 ;;

(* 3 états : état 0 -> 6 transitions  état 1 -> 28 transitions   etat 2 -> 12 conditions*)

let (automate_zombie: automate) = 
  presence_AD 0 Ennemi Attaquer 0 1
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1;;



let equipe2 = [(guerrier,"guerrier"); (farmer,"farmer")];; 

(* let equipe = [(automate_zombie, "zombie")];; *)