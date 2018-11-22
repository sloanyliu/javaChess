/*Sloan Liu || 11/19/18 || Rook.java

This is the rook piece that extends from the ChessPiece class
*/

import java.io.*;
import java.lang.*;
import java.util.Scanner;




//****************************ROOK**************************************************************


class Rook extends ChessPiece {

    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Rook(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean MoveTo(int col, int row, Pieces board) {
        if(this.col == col || this.row == row) {
            int[] spaces = this.GetPath(col, row); //get the path
            
            if(spaces[0] == 0) { //one space move, always possible
                return true;
            } else if(spaces[0] == -1) { //same space, not a move
                return false;
            }

            //check if there is any piece blocking that path
            for(int i = 0; i < spaces.length; i+=2) {
                //if we dont find anything, then move on
                if(board.Find(spaces[i], spaces[i+1]) == null) {
                    ;
                } else { //if its found, path is blocked
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
            //Horizontal and vertical check
            if(this.col == other.col || this.row == other.row) {
                return true;
            } else {
                return false;
            }
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "R";
        } else {
            return "r";
        }
    }


    int[] GetPath(int c1, int r1) {

        //getting number of spots between start and dest
        int len = 0;
        
        if(this.col == c1) {
            len = Math.abs(this.row - r1) - 1;
        } else if(this.row == r1) {
            len = Math.abs(this.col - c1) - 1;
        } else {
            len = Math.abs(this.col - c1) - 1; 
        }

        
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

        if(this.col == c1 && (this.row - r1) > 0) {
            for(int d = 0; d < len*2; d+=2) {
                x[d] = col;
                x[d+1] = row - 1;
                row = row - 1;
            }
        } else if(this.col == c1 && (this.row - r1) < 0) {
            for(int h = 0; h < len*2; h+=2) {
                x[h] = col;
                x[h+1] = row + 1;
                row = row + 1;
            }
        } else if((this.col - c1) > 0 && this.row == r1) {
            for(int r = 0; r < len*2; r+=2) {
                x[r] = col - 1;
                x[r+1] = row;
                col = col - 1;
            }
        } else if((this.col - c1) < 0 && this.row == r1) {
            for(int f = 0; f < len*2; f+=2) {
                x[f] = col + 1;
                x[f+1] = row;
                col = col + 1;
            }
        }
        return x;
    }
}



