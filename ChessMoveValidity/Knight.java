/*Sloan Liu || 11/19/18 || Knight.java

This is the Knight piece that extends the chess piece class

*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;



//**************************KNIGHT***************************************************************


class Knight extends ChessPiece {

    ChessPiece next; //next node

    //Constructor
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Knight(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    /*
    Checking if a physical move is possible

    Knight can move one/two in one direction then two/one in another
    As long as it makes an L shape, orientation does not matter
    No diagonal moving

    If physical move is possible -> return true
    If physical move is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        //row difference is 2 and col difference is 1
        if(Math.abs(this.col - col) == 1 && Math.abs(this.row - row) == 2) {
            return true;
        //row difference is 1 and col difference is 2
        } else if(Math.abs(this.row - row) == 1 && Math.abs(this.col - col) == 2) {
            return true;
        //if none of the configurations from above, then no move
        } else {
            return false;
        }
    }

    /*
    Checking is this piece can attack another piece, regardless of path

    Knights can jump over pieces, so there is no need to use the 
    get path function here to check for path

    If attack is possible -> return true
    If attack is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean IsAttacking(ChessPiece other) {
        //If the same coloe then no attack
        if(this.color == other.color) {
            return false;
        } else {
            //col difference is 1 and row difference is 2
            if(Math.abs(this.col - other.col) == 1 && Math.abs(this.row - other.row) == 2) {
                return true;
            //row difference is 1 and col difference is 2
            } else if(Math.abs(this.row - other.row) == 1 && Math.abs(this.col - other.col) == 2) {
                return true;
            //if none of the cases from above, then attack is invalid
            } else {
                return false;
            }
        }
    }


    //Gets the type of the piece depedning on the color
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "N";
        } else {
            return "n";
        }
    }

    //gets the path
    //No real need for this since Knight can jump over pieces
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    int[] GetPath(int col, int row) {
        int[] x = new int[1];

        //Trying to move to the same space
        if(this.col == col && this.row == row) {
            x[0] = -1;
        //moving one space away
        } else {
            x[0] = 0;
        }

        return x;
    }
}

