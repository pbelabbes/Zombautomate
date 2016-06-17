OBJ= doc.cmo user1.cmo user2.cmo fichier_zombies.cmo demo1.cmo demo2.cmo demo3.cmo main.cmo
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

demo1.cmo: demo1.ml doc.cmi
	ocamlc -c demo1.ml

demo2.cmo: demo2.ml doc.cmi
	ocamlc -c demo2.ml

demo3.cmo: demo3.ml doc.cmi
	ocamlc -c demo3.ml

main.cmo: doc.cmi user1.cmo user2.cmo fichier_zombies.cmo demo1.ml demo2.ml demo3.ml main.ml
	ocamlc -c main.ml

clean:
	rm -f -v *.cmi *.cmo

