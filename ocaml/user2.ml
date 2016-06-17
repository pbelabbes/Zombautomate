open Doc ;;
(* Cette instruction permet d'utiliser les fonctionnalitÃ©s proposÃ©es dans la premiÃ¨re version du manuel de l'utilisateur. *)

(*DÃ©finissez ici vos automates ! (le langage Ã  utiliser est l'OCaml.) Veuillez vous rÃ©fÃ©rer au manuel de l'utilisateur pour crÃ©er correctement les automates. *)
let exemple2 = [(0,Defaut,Deplacer,S,1,1)] @ [(1,Defaut,Attaquer,S,0,1)]




(* utilisez la variable equipe2 pour valider votre Ã©quipe. Le type Ã  respecter est (automate*String) list *)

let equipe2 = [(exemple2,"exemple_nom")]
