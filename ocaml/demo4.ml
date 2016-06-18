open Doc;;

let (demotomate:automate) = [(0,Defaut,Deplacer,N,0,1)];;

let (demotomate2:automate) = 
    [(0,Case_alliee(E),Deposer,E,0,10)]
  @  [(0,Defaut,Deplacer,E,0,1)];;


let demo4 = [(demotomate,"d1") ; (demotomate2, "d2")];;
