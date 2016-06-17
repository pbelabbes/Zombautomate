OBJ= doc.cmo user1.cmo user2.cmo fichier_zombies.cmo main.cmo
BIN = xml_writter


all : xml_writter clean

xml_writter : $(OBJ)
	ocamlc $(OBJ) -o $(BIN)

doc.cmi : doc.mli
	ocamlc -c doc.mli

doc.cmo : doc.cmi doc.ml
	ocamlc -c doc.ml

user1.cmo: user1.ml doc.cmi
	ocamlc -c user1.ml 

user2.cmo: user2.ml doc.cmi
	ocamlc -c user2.ml 

fichier_zombies.cmo: fichier_zombies.ml doc.cmi
	ocamlc -c fichier_zombies.ml 

main.cmo: doc.cmi user1.cmo user2.cmo fichier_zombies.cmo main.ml
	ocamlc -c main.ml

clean:
	rm -f -v *.cmi *.cmo

