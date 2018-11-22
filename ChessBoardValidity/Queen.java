
import java.io.*;
import java.lang.*;
import java.util.Scanner;




//**************************QUEEN***************************************************************


class Queen extends ChessPiece {
    
    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Queen(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean isAttacking(ChessPiece other) {
        if(this.color == other.color) {
            return false;
        } else {
            //Horizontal and vertical check
            if(this.col == other.col || this.row == other.row) {
                return true;
            //Diagonal check
            } else if(Math.abs(this.col - other.col) == Math.abs(this.row - other.row)) {
                return true;
            } else {
                return false;
            }
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "Q";
        } else {
            return "q";
        }
    }
}

