open Doc ;;

let (guerrier: automate) =
  presence_AD 0 Katana Ramasser 1 4
  @
    presence_AD 0 Batte_baseball Ramasser 1 3
  @
    scan_loin_AD 0 Katana Deplacer 0 2
  @
    scan_loin_AD 0 Batte_baseball Deplacer 0 1
  @
    presence_AD 1 Rocher Attaquer 2 7 
  @ 
    presence_AD 1 Ennemi Attaquer 2 7
  @
    presence_AD 1 Zombie Attaquer 2 6
  @
     scan_loin_AD 1 Ennemi Deplacer 1 5
  @
    scan_loin_AD 1 Batte_baseball Deplacer 1 1
  @
    scan_loin_AD 1 Katana Deplacer 1 2
  @
    presence_AD 1 Batte_baseball Ramasser 1 3
  @
    presence_AD 1 Katana Ramasser 1 4
  @   
     presence_AD 2 Ennemi Attaquer 2 7
  @
    presence_AD 2 Zombie Attaquer 2 6
 @
    presence_AD 2 Rocher Attaquer 2 7 
  @
    presence_AD 2 Katana Ramasser 1 4
  @
    presence_AD 2 Batte_baseball Ramasser 1 3
  @
    scan_loin_AD 2 Katana Deplacer 1 2
  @
    scan_loin_AD 2 Batte_baseball Deplacer 1 1
  @
    [(0,Defaut,Deplacer,O,1,0)]
  @
    [(1,Defaut,Deplacer,O,1,0)] 
  @
    [(0,Defaut,Deplacer,S,1,0)]
  @
    [(1,Defaut,Deplacer,S,1,0)] ;;

(*guerrier : 2 Ã©tats chacun 16 transitions ... plus Ã  jour*)



let (farmer: automate) = 
  [(0, Et(Case_ennemie(N),Ou(Present(Lapin,N),Present(Pomme,N))), Deplacer, N, 0, 1)]
  @
    presence_AD 0 Pomme Ramasser 0 3
  @
    presence_AD 0 Lapin Ramasser 0 4
  @
    scan_loin_AD 0 Pomme Deplacer 0 2
  @
    scan_loin_AD 0 Lapin Deplacer 0 1
  @
    presence_AD 0 Herbe Planter 1 0
  @
    presence_AD 1 Pousse Arroser 2 1
  @
    presence_AD 2 Arbre Attaquer 0 1
  @
    [(0,Ou(Present(Ennemi,N),Present(Ennemi,E)), Deplacer, S, 0, 3)]
  @
    [(0,Ou(Present(Ennemi,S),Present(Ennemi,O)), Deplacer, N, 0, 3)]
  @
    presence_AD 0 Ennemi Voler 0 1 ;;


(* 3 Ã©tats : Ã©tat 0 -> 6 transitions  Ã©tat 1 -> 28 transitions   etat 2 -> 12 conditions ... plus Ã  jour*)



let (automate_zombie: automate) = 
  presence_AD 0 Ennemi Attaquer 0 1
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1;;

let (simplet:automate) = 
  presence_AD 0 Ennemi Attaquer 0 2
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1;;


let equipe1 = [ (guerrier,"guerrier"); (guerrier,"guerrier");(guerrier,"guerrier"); (guerrier,"guerrier")];; 
(* let equipe = [(automate_zombie, "zombie")];; *)
