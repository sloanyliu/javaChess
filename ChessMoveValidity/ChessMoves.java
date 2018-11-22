import java.io.*;
import java.lang.*;
import java.util.Scanner;




public class ChessMoves {

    public static void main(String[] args) throws IOException {

        //Making sure there is input file and output file
        if(args.length < 2) {
            System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
            System.exit(1);
        }

        //Opening the files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        //out.print("\n");
        
        int row = 0; //col of new piece
        int col = 0; //row of new piece
        String type = ""; //type of new piece

        boolean valid = true;
        //boolean isHit = false;
        //ChessPiece check;


        //Reading the lines of input files and tokenizing them
        while(in.hasNextLine()) {
            //Take away trailing spaces, then add 1 trailing to 
            //catch blank lines
            String line = in.nextLine().trim() + " ";

            //split line around white space
            String[] token = line.split("\\s+"); //String array with all the tokens in one line

            int n = token.length;
            
            Pieces board = new Pieces(); //LinkedList
            
            //done flag is signal transition between building board and movinf pieces
            int done = 0;

            int findCol = 0; //col to be moved
            int findRow = 0; //row to be moved

            int moveCol = 0; //col we are moving to
            int moveRow = 0; //row we are moving to

            //This loop fills the LinkedList
            //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
            int i = 0;
            for(i = 0; i < n; i += 3) {
                type = token[i];
                col = Integer.parseInt(token[i+1]);

                //detecting whether we have reached the end of building the board
                if(token[i+2].length() > 1) {
                    row = Integer.parseInt(token[i+2].substring(0,1));
                    done = 1; //set done flag to 1, last piece to put on board
                } else {
                    row = Integer.parseInt(token[i+2]);
                }

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
                
                //if piece about to placed is not on board, piece can be placed
                if(board.Find(piece.col, piece.row) == null) {
                    board.Insert(piece); //place the piece
                //if piece about to be placed is on the board already
                } else {
                    valid = false; //cannot place piece
                    board.Insert(piece); //place the piece
                }

                //if done flag high, then move on to next part of analysis
                if(done == 1) {
                    i = i + 3; //now the indx is on the next token ready to be analyzed.
                    break; //break out of board-building loop
                }
            }
            //<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>



            //If the valid flag is false, then this board is no good
            if(valid == false) {
                findCol = Integer.parseInt(token[i]);
                findRow = Integer.parseInt(token[i+1]);

                //get dimensions for where we want to move to
                moveCol = Integer.parseInt(token[i+2]);
                moveRow = Integer.parseInt(token[i+3]);

                out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                valid = true; //reset the valid flag
                continue; //move on to the next line
            } 

            //second part of analysis
            //[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]


            //If kings were successfully gotten
            if(board.GetKings() == true) { 

                //0 is white and 1 is black
                //turn = 0 is white's turn and turn = 1 is black's turn
                int turn = 0;
            
                boolean legal = true; //legal move flag
                int firstMove = 0; //flag to check for first move

                //loop to iterate through all the potential moves
                for(int j = i; j < n; j+=4) {
                    
                    //get dimensions for the piece we want to move
                    findCol = Integer.parseInt(token[j]);
                    findRow = Integer.parseInt(token[j+1]);

                    //get dimensions for where we want to move to
                    moveCol = Integer.parseInt(token[j+2]);
                    moveRow = Integer.parseInt(token[j+3]);
                    
                    //First we make sure there is a piece at the desired location
                    ChessPiece src = board.Find(findCol, findRow);

                    //if we cant find the src, then move is illegal
                    if(src == null) {
                        // ILLEGAL MOVE
                        out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                        legal = false;
                        break;
                    //if src is there, then continue with algorithm
                    //checking to see if there can obstructions in the path
                    } else {
                        
                        //White should move first, if black moves first, illegal
                        if(src.color == 1 && firstMove == 0) {
                            // ILLEGAL MOVE
                            out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                            legal = false;
                            break;
                        //Otherwise, toggle flag to indicate first move has passed already
                        } else {
                            firstMove = 1;
                        }


                        //0 == 0 or 1 == 1
                        //if the color matches the turn order then the move is already started to be valid
                        if(src.color == turn) {
                            //swapping the tunr variabelt o check for the next turn being valid
                            if(turn == 0) {
                                turn = 1;
                            } else if(turn == 1) {
                                turn = 0;
                            }
                        //of the color and turn do not match then one side has moved when they were not supposed to
                        } else if(src.color != turn) {
                            out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                            legal = false;
                            break;
                        }





                        //If there is a piece blocking the path, then move is illegal
                        if(src.MoveTo(moveCol, moveRow, board) == false) {
                            
                            //ILLEGAL MOVE
                            out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");

                            legal = false;
                            break;
                        //If there is nothing blocking the path, continue with algorithm
                        } else {
                            
                            //Check if there is a piece at the location we are moving to
                            ChessPiece dest = board.Find(moveCol, moveRow);

                            //If there is nothing at the dest location
                            if(dest == null) {

                                //Check if its a pawn
                                if(src.GetType().equals("P")) { //Black Pawn
                                    ChessPiece temp = new ChessPiece(moveCol, moveRow, 0);
                                    //move is diagonal without an attack
                                    if(src.IsAttacking(temp) == true) {
                                        //ILLEGAL MOVE
                                        out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                        legal = false;
                                        break;
                                    }
                                } else if(src.GetType().equals("p")) { //White Pawn
                                    ChessPiece temp = new ChessPiece(moveCol, moveRow, 1);
                                    //move is diagoanl without an attack
                                    if(src.IsAttacking(temp) == true) {
                                        //ILLEGAL MOVE
                                        out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                        legal = false;
                                        break;
                                    }
                                }
                                
                                //Move the piece
                                src.col = moveCol;
                                src.row = moveRow;

                                //Check for king being in check
                                if(j + 4 == n) { //last move, all kings must be safe
                                    if(board.IsKingSafe(0) == true && board.IsKingSafe(1) == true) {
                                        ; //LEGAL
                                    } else {
                                        //ILLEGAL MOVE
                                        out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                        legal = false;
                                        break;
                                    }
                                //otherwiase just worry about src's king
                                } else { 
                                    if(board.IsKingSafe(src.color) == true) {
                                        ; //LEGAL
                                    } else {
                                        //ILLEGAL MOVE
                                        out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                        legal = false;
                                        break;
                                    }
                                }

                            //If there is something at the location we want to move to
                            //see if source can attack that piece
                            } else {
                                
                                //The two pieces are the same color
                                if(src.IsAttacking(dest) == false) {
                                    //ILLEGaL MOVE
                                    out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                    legal = false;
                                    break;
                                //If the two pieces are different colors
                                } else {
                                    
                                    //Delete the piece at the dest location
                                    ChessPiece temp = board.Delete(dest.col, dest.row);

                                    //move the src
                                    src.col = temp.col;
                                    src.row = temp.row;

                                    //Check if the King is in check
                                    if(j + 4 == n) { //last move, all kings must be safe
                                        if(board.IsKingSafe(0) == true && board.IsKingSafe(1) == true) {
                                            ; //LEGAL
                                        } else {
                                            //ILLEGAL MOVE
                                            out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                            legal = false;
                                            break;
                                        }
                                    //otherwiase just worry about src's king
                                    } else { 
                                        if(board.IsKingSafe(src.color) == true) {
                                            ; //LEGAL
                                        } else {
                                            //ILLEGAL MOVE
                                            out.println(findCol + " " + findRow + " " + moveCol + " " + moveRow + " illegal");
                                            legal = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //Check for the legal flag
                if(legal == true) {
                    out.println("legal");
                } else {
                    ;
                }
            } else {
                System.out.println("GetKing Error");
                System.exit(1);
            }
            //[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]
        
        }

        //closing time
        in.close();
        out.close();
    }

}



