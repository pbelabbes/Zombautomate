open Doc;;

let (automate_zombie: automate) = 
  presence_AD 0 Ennemi Attaquer 0 2
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1
  @
  [(0,Defaut,Deplacer,N,0,0)]
  @
  [(0,Defaut,Deplacer,O,0,0)]
  @
  [(0,Defaut,Deplacer,E,0,0)]
  @
  [(0,Defaut,Deplacer,S,0,0)]
;;




let zombies = [(automate_zombie, "zombie")];;
