
import java.io.*;
import java.lang.*;
import java.util.Scanner;




//***************************KING***************************************************************


class King extends ChessPiece {
    
    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public King(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean isAttacking(ChessPiece other) {
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


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "K";
        } else {
            return "k";
        }
    }
}

