open Doc;;

let (automate_zombie: automate) = 
  presence_AD 0 Ennemi Attaquer 0 2
  @
  scan_loin_AD 0 Ennemi Deplacer 0 1;;




let zombies = [(automate_zombie, "zombie")];;
