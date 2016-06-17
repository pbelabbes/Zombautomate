open Doc;;

let (automate_demo1:automate) = 
  [(0,Defaut,Deplacer,E,1,1)]
  @
  [(1,Defaut,Deplacer,E,1,1)]
  @
  [(1,Presence(Rocher,E),Attaquer,E,2,2)]
  @
  [(2,Presence(Zombie,E),Attaquer,E,2,2)]
  @
  [(2,Defaut,Deplacer,E,2,1)];;

let demo1 = [(automate_demo1, "demo1")];;
