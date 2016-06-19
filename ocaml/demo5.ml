open Doc

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
    presence_AD 2 Arbre Attaquer 0 1;;




let (stealer:automate) = 
  [(0, Defaut, Deplacer, N,0,1)]
  @
  [(0,Present(Ennemi,N),Voler,N,0,5)];;

let demo5= [(farmer,"p1")];;
let demo5_2 = [(stealer,"p2")];;
