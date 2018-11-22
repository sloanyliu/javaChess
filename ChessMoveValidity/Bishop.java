/*Sloan Liu || 11/19/18 || Bishop.java

This is the Bishop piece that extends from ChessPiece class
*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;




//**************************BISHOP**************************************************************


class Bishop extends ChessPiece {

    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Bishop(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        if(Math.abs(this.col - col) == Math.abs(this.row - row)) {
            int[] spaces = this.GetPath(col, row);

            if(spaces[0] == 0) { //one space move, always possible
                return true;
            } else if(spaces[0] == -1) { //same space, not a move
                return false;
            }


            for(int i = 0; i < spaces.length; i+=2) {
                if(board.Find(spaces[i], spaces[i+1]) == null) {
                    ;
                } else {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean IsAttacking(ChessPiece other) {
        if(this.color == other.color) {
            return false;
        } else {
            //Diagonal check
            if(Math.abs(this.col - other.col) == Math.abs(this.row - other.row)) {
                return true;
            } else {
                return false;
            }
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "B";
        } else {
            return "b";
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    int[] GetPath(int c1, int r1) {

        //getting number of spots between start and dest
        int len = Math.abs(this.col - c1) - 1;

        //move is only one space away
        if(len == 0) { 
            int[] x = new int[1];
            x[0] = 0;
            return x;
        //destination is the same spot as beginning
        } else if(len < 0) {
            int[] x = new int[1];
            x[0] = -1;
            return x;
        }


        int[] x = new int[len*2]; //one spot has col and row
    
        int col = this.col;
        int row = this.row;

        if((this.col - c1) > 0 && (this.row - r1) > 0) {
            for(int i = 0; i < len*2; i+=2) {
                x[i] = col - 1;
                x[i+1] = row - 1;
                col = col - 1;
                row = row - 1;
            }
        } else if((this.col - c1) > 0 && (this.row - r1) < 0) {
            for(int j = 0; j < len*2; j+=2) {
                x[j] = col - 1;
                x[j+1] = row + 1;
                col = col - 1;
                row = row + 1;
            }
        } else if((this.col - c1) < 0 && (this.row - r1) > 0) {
            for(int u = 0; u < len*2; u+=2) {
                x[u] = col + 1;
                x[u+1] = row - 1;
                col = col + 1;
                row = row - 1;
            }
        } else if((this.col - c1) < 0 && (this.row - r1) < 0) {
            for(int y = 0; y < len*2; y+=2) {
                x[y] = col + 1;
                x[y+1] = row + 1;
                col = col + 1;
                row = row + 1;
            }   
        }
            
        return x;
    }
}



