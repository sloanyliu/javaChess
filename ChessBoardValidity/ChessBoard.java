import java.io.*;
import java.lang.*;
import java.util.Scanner;


public class ChessBoard {

    public static void main(String[] args) throws IOException {
        int Qcol = 0;
        int Qrow = 0;

        String type = "";
        int row = 0;
        int col = 0;

        boolean valid = true;
        ChessPiece query;
        boolean isHit = false;
        ChessPiece check;


        //Making sure there is input file and output file
        if(args.length < 2) {
            System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
            System.exit(1);
        }

        //Opening the files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        //out.print("\n");
        
        //Reading the lines of input files and tokenizing them
        while(in.hasNextLine()) {
            //Take away trailing spaces, then add 1 trailing to 
            //catch blank lines
            String line = in.nextLine().trim() + " ";

            //split line around white space
            String[] token = line.split("\\s+"); //String array with all the tokens in one line
            
            Qcol = Integer.parseInt(token[0]);
            Qrow = Integer.parseInt(token[1].substring(0,1));

            int n = token.length;
            
            Pieces board = new Pieces(); //LinkedList

            //This loop fills the LinkedList
            for(int i = 2; i < n - 2; i += 3) {
                type = token[i];
                col = Integer.parseInt(token[i+1]);
                row = Integer.parseInt(token[i+2]);

                ChessPiece piece = null;

                if(type.equals("K")){ //Black King
                    piece = new King(col, row, 1);
                    
                } else if(type.equals("k")) { //White King
                    piece = new King(col, row, 0);

                } else if(type.equals("Q")) { //Black Queen
                    piece = new Queen(col, row, 1);

                } else if(type.equals("q")) { //White Queen
                    piece = new Queen(col, row, 0);

                } else if(type.equals("R")) { //Black Rook
                    piece = new Rook(col, row, 1);

                } else if(type.equals("r")) { //White Rook
                    piece = new Rook(col, row, 0);

                } else if(type.equals("N")) { //Black Knight
                    piece = new Knight(col, row, 1);

                } else if(type.equals("n")) { //White Knight
                    piece = new Knight(col, row, 0);

                } else if(type.equals("B")) { //Black Bishop
                    piece = new Bishop(col, row, 1);

                } else if(type.equals("b")) { //White Bishop
                    piece = new Bishop(col, row, 0);

                } else if(type.equals("P")) { //Black Pawn
                    piece = new Pawn(col, row, 1);

                } else if(type.equals("p")) { //White Pawn
                    piece = new Pawn(col, row, 0);
                }
                
                //Add the piece to the board
                
                if(board.Find(piece.col, piece.row) == null) {
                    board.Insert(piece);
                } else {
                    valid = false;
                    break;
                }
            }

            if(valid == false) {
                out.println("Invalid");
                valid = true;
            //If the board is valid
            } else {
                query = board.Find(Qcol, Qrow); //find the query

                //if the query does not exist
                if(query == null) {
                    out.println("-"); //print "-" and move one
                //otherwise if it exists
                } else {
                    out.print(query.GetType() + " "); //print the type
                    
                    check = board.head; //get the head for attack checking

                    //loop through and check for attacking pieces
                    while(check != null) {
                        isHit = query.isAttacking(check); //saving the hit
                        
                        //if any hit, we are done
                        if(isHit == true) {
                            out.print("y\n");
                            break;
                        } 

                        check = check.next; //loop increment
                    }

                    //if hit stays false the whole time
                    if(isHit == false) {
                        out.print("n\n");
                    }

                }

            }
            

        }

        //closing time
        in.close();
        out.close();


    }


}


