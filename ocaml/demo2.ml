open Doc;;

let (guerrier: automate) =
  presence_AD 0 Katana Ramasser 1 1
  @
    presence_AD 0 Batte_baseball Ramasser 1 1
  @
    scan_loin_AD 0 Katana Deplacer 0 1
  @
    scan_loin_AD 0 Batte_baseball Deplacer 0 1
  @
    presence_AD 1 Ennemi Attaquer 1 7
  @
    presence_AD 1 Zombie Attaquer 1 6
  @
    scan_loin_AD 1 Ennemi Deplacer 1 5
  @
    scan_loin_AD 1 Zombie Deplacer 1 5
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
    [(0,Defaut,Deplacer,O,1,0)]
  @
    [(1,Defaut,Deplacer,O,1,0)] 
  @
    [(0,Defaut,Deplacer,S,1,0)]
  @
    [(1,Defaut,Deplacer,S,1,0)] ;;

let demo2= [(guerrier,"variaur")];;
