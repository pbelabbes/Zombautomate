OBJ= doc.cmo exemple_fichier_user1.cmo exemple_fichier_user2.cmo fichier_zombies.cmo main.cmo
BIN = xml_writter


all : xml_writter clean

xml_writter : $(OBJ)
	ocamlc $(OBJ) -o $(BIN)

doc.cmi : doc.mli
	ocamlc -c doc.mli

doc.cmo : doc.cmi doc.ml
	ocamlc -c doc.ml

exemple_fichier_user1.cmo: exemple_fichier_user1.ml doc.cmi
	ocamlc -c exemple_fichier_user1.ml 

exemple_fichier_user2.cmo: exemple_fichier_user2.ml doc.cmi
	ocamlc -c exemple_fichier_user2.ml 

fichier_zombies.cmo: fichier_zombies.ml doc.cmi
	ocamlc -c fichier_zombies.ml 

main.cmo: doc.cmi exemple_fichier_user1.cmo exemple_fichier_user2.cmo fichier_zombies.cmo main.ml
	ocamlc -c main.ml

clean:
	rm -f -v *.cmi *.cmo

