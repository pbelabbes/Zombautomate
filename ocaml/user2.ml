open Doc ;;

<<<<<<< HEAD
(*DÃ©finissez ici vos automates ! (le langage Ã  utiliser est l'OCaml.) Veuillez vous rÃ©fÃ©rer au manuel de l'utilisateur pour crÃ©er correctement les automates. *)
let exemple2 = [(0,Defaut,Deplacer,S,1,1)] @ [(1,Defaut,Attaquer,S,0,1)]




(* utilisez la variable equipe2 pour valider votre Ã©quipe. Le type Ã  respecter est (automate*String) list *)

let equipe2 = [(exemple2,"exemple_nom")]
=======
let (guerrier: automate) =
  presence_AD 0 Katana Ramasser 1 1
  @
    presence_AD 0 Batte_baseball Ramasser 1 1
  @
    scan_loin_AD 0 Katana Deplacer 0 1
  @
    scan_loin_AD 0 Batte_baseball Deplacer 0 1
  @
    presence_AD 1 Ennemi Attaquer 1 6
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
    presence_AD 0 Rocher Attaquer 0 7
  @
    presence_AD 1 Rocher Attaquer 1 7  
  @
    [(0,Defaut,Deplacer,E,1,0)]
  @
    [(1,Defaut,Deplacer,E,1,0)]
  @
    [(0,Defaut,Deplacer,N,1,0)]
  @
    [(1,Defaut,Deplacer,N,1,0)] ;;


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
  presence_AD 0 Ennemi Attaquer 0 3
  @
  scan_loin_AD 0 Ennemi Deplacer 0 2
  @
  [(0,Defaut, Deplacer, N, 0, 1)];;


let equipe2 = [ (guerrier,"guerrier"); (guerrier,"guerrier");(guerrier,"guerrier"); (guerrier,"guerrier")];; 
(* let equipe = [(automate_zombie, "zombie")];; *)
>>>>>>> f9dcb24f4c176dba4702ae931e8ce8d08149c0d5
