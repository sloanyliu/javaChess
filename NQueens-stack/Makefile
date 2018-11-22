
#  -------------------------------------
#  A Makefile with macros
#  -------------------------------------

JAVASRC = NQueens.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = NQueens
CLASSES = NQueens.class Queen.class
JARFILE = NQueens.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm -f $(JARFILE) $(CLASSES)


