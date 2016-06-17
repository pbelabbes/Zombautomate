open Doc ;;
(* Cette instruction permet d'utiliser les fonctionnalités proposées dans la première version du manuel de l'utilisateur. *)

(*Définissez ici vos automates ! (le langage à utiliser est l'OCaml.) Veuillez vous référer au manuel de l'utilisateur pour créer correctement les automates. *)
let exemple2 = [(0,Defaut,Deplacer,S,1,1)] @ [(1,Defaut,Attaquer,S,0,1)]




(* utilisez la variable equipe2 pour valider votre équipe. Le type à respecter est (automate*String) list *)

let equipe2 = [(exemple2,"exemple_nom")]
