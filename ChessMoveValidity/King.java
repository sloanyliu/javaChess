/* Sloan Liu || 11/19/18 || King.java

This is the King Piece that extends the ChessPiece class

*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;




//***************************KING***************************************************************


class King extends ChessPiece {
    
    ChessPiece next; //next node

    //Constructor
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public King(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    /*
    Checking if a physical move is possible

    Since the King can only move one piece, most of the time it is valid
    No need to check the path

    If physical move is possible -> return true
    If physical move is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        //Same col, and row is one away 
        if(this.col == col && Math.abs(this.row - row) == 1) {
            return true;
        //Same row, and col is one away
        } else if(this.row == row && Math.abs(this.col - col) == 1) {
            return true;
        //In the diagonals
        } else if(Math.abs(this.col - col) == 1 && Math.abs(this.row - row) == 1) {
            return true;
        //If its none of the above, then the move you wanna move to is not valid
        } else {
            return false;
        }
    }


     /*
    Checking is this piece can attack another piece, regardless of path

    Anywhere around the King that is one space away is good for attack

    If attack is possible -> return true
    If attack is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean IsAttacking(ChessPiece other) {
        //If the same color, then attack is not valid
        if(this.color == other.color) {
            return false;
        } else {
            //same column, and row is one away
            if(this.col == other.col && Math.abs(this.row - other.row) == 1) {
                return true;
            //Same row, and col is one away
            } else if(this.row == other.row && Math.abs(this.col - other.col) == 1) {
                return true;
            //In the diagonals
            } else if(Math.abs(this.col - other.col) == 1 && Math.abs(this.row - other.row) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }


    //gets the type of the piece based on the color of the piece
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "K";
        } else {
            return "k";
        }
    }


    //All of the King's path will be either one space or no space
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



