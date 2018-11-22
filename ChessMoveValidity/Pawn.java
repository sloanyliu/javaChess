/* Sloan Liu || 11/19/18 || Pawn.class

This is the pawn piece that extends the ChessPiece class

Specifics:
~ White Pawns would start in row 2 at the beginning of a game
    - White Pawn movement would be INCREASING rows
~ Black Pawns ...............row 7...........................
    - Black Pawn movement would be DECREASING rows

*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;



//**************************PAWN****************************************************************


class Pawn extends ChessPiece {

    ChessPiece next; //next node

    //Constructor
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Pawn(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    /*
    Checks if its physically possible to move somewhere, including attack path
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        int[] spaces = this.GetPath(col, row);

        //move is one space away
        if(spaces[0] == 0) {
            return true;
        //invalid move
        } else if(spaces[0] == -1) {
            return false;
        //If there is actually something on the path
        } else {
            //nothing there, move is valid
            if(board.Find(spaces[0], spaces[1]) == null) {
                return true;
            //othwerwise its an illegal move
            } else {
                return false;
            }
        }
    }


    /*
    Checks the validity of an attack with the pawn

    Cant attack on starting row
    Anywhere else, attack must be a diagonal
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean IsAttacking(ChessPiece other) {
        if(this.color == other.color) {
            return false;
        } else {
            //White Pawn --> inc row move
            if(this.color == 0) {
                //Pawns cannot attack at starting position
                if(this.row == 2) {
                    return false;
                //Diagonal one way
                } else if((other.col - this.col) == 1 && (other.row - this.row) == 1) {
                    return true;
                //Diagonal the other way
                } else if((other.col - this.col) == -1 && (other.row - this.row) == 1) {
                    return true;
                } else {
                    return false;
                }
            //Black Pawn --> dec row move
            } else if(this.color == 1) {
                //Pawns cannot attack at starting position
                if(this.row == 7) {
                    return false;
                //diagonal one way
                } else if((other.col - this.col) == -1 && (other.row - this.row) == -1) {
                    return true;
                //diagonal the other way
                } else if((other.col - this.col) == -1 && (other.row - this.row) == -1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "P";
        } else {
            return "p";
        }
    }


    //Gets the path of move for a Pawn
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    int[] GetPath(int c1, int r1) {
        int[] x = new int[2];

        //White Pawn
        if(this.color == 0) {
            //At the Starting row
            if(this.row == 2) {
                //if move is one row ahead and on same col
                if(r1 - this.row == 1 && this.col == c1) {
                    x[0] = 0; //valid path of no spaces
                //if move is two rows ahead and on same col
                } else if(r1 - this.row == 2 && this.col == c1) {
                    x[0] = c1;
                    x[1] = r1;
                //anything else on the starting row is invalid
                } else {
                    x[0] = -1;
                }
            //If not at the starting row
            } else {
                //Row must be one ahead and column must be same or one away
                if(r1 - this.row == 1 && Math.abs(c1 - this.col) <= 1) {
                    x[0] = 0;
                //anything else is invalid
                } else {
                    x[0] = -1;
                }
            }
        //Black Pawn
        } else if(this.color == 1) {
            //At the starting row
            if(this.row == 7) {
                //if move is one row back and on same col
                if(r1 - this.row == -1 && this.col == c1) {
                    x[0] = 0;
                //if move is two rows back and on same col
                } else if(r1 - this.row == -2 && this.col == c1) {
                    x[0] = c1;
                    x[1] = r1;
                //anything else on the starting row is invalid
                } else {
                    x[0] = -1;
                }
            //If not at the starting row
            } else {
                //Row must be one behind and column must be same or one away
                if(r1 - this.row == -1 && Math.abs(c1 - this.col) <= 1) {
                    x[0] = 0;
                //anything else is invalid
                } else {
                    x[0] = -1;
                }
            }
        }
        return x;
    }
    

}


