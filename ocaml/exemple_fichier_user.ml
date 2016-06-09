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





let equipe = [(guerrier,"guerrier")];;
