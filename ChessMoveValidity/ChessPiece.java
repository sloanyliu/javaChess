/*
Sloan Liu || 11/19/2018 || ChessPiece.java

This is the parent class of all the chess piece classes:
~ Queen
~ King
~ Bishop
~ Rook
~ Knight
~ Pawn

All the above classes will be used to in creating a Chess Board (LinkedList)
*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;



//**********************************************************************************************
//******************CHESS PIECE CLASSES********************************************************
//**********************************************************************************************


class ChessPiece {
    //Variable accessible by this class
    int col; //column
    int row; //row
    int color; //0 is white -> lowercase
               //1 is black -> CAPITAL
    ChessPiece next; //next item after this one
   
    //Constructor
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public ChessPiece(int c, int r, int color) {
        this.col = c; //storing the column
        this.row = r; //storing the row
        this.color = color; //storing the color
        this.next = null; //the next object
    }


    /*
    Checking if a physical move is possible
    If physical move is possible -> return true
    If physical move is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        return false;
    }


    /*
    Checking is this piece can attack another piece, regardless of path
    If attack is possible -> return true
    If attack is not possible -> return false
    */
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean IsAttacking(ChessPiece other) {
        return false;
    }


    //gets the type of the piece based on the color
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        return "";
    }


    //Gets the path of the pieces move
    int[] GetPath(int c1, int r1) {
        int[] x = new int[0];
        return x;
    }

}



