#  -------------------------------------
#  A Makefile with macros
#  -------------------------------------

JAVASRC = ChessBoard.java Pieces.java ChessPiece.java Queen.java King.java Knight.java Rook.java Bishop.java Pawn.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = ChessBoard
CLASSES = ChessBoard.class Pieces.class ChessPiece.class Queen.class King.class Knight.class Rook.class Bishop.class Pawn.class
JARFILE = ChessBoard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm -f $(JARFILE) $(CLASSES)
