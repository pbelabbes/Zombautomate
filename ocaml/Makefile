OBJ= doc.cmo exemple_fichier_user.cmo main.cmo
BIN = xml_writter

all : xml_writter clean run

xml_writter : doc.cmo exemple_fichier_user.cmo main.cmo
	ocamlc $(OBJ) -o $(BIN)

doc.cmi : doc.mli
	ocamlc -c doc.mli

doc.cmo : doc.cmi doc.ml
	ocamlc -c doc.ml

exemple_fichier_user.cmo: exemple_fichier_user.ml doc.cmi
	ocamlc -c exemple_fichier_user.ml 

main.cmo: doc.cmi exemple_fichier_user.cmo main.ml
	ocamlc -c main.ml

clean:
	rm -f -v *.cmi *.cmo

