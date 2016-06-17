open Doc ;;
(* Cette instruction permet d'utiliser les fonctionnalités proposées dans la première version du manuel de l'utilisateur. *)


(*Définissez ici vos automates ! (le langage à utiliser est l'OCaml.) Veuillez vous référer au manuel de l'utilisateur pour créer correctement les automates. *)
let exemple1 = [(0,Defaut,Deplacer,E,1,1)] @ [(1,Defaut,Attaquer,E,0,1)]



(* utilisez la variable equipe1 pour valider votre équipe. Le type à respecter est (automate*String) list *)

let equipe1 = [(exemple1,"exemple_nom")]
