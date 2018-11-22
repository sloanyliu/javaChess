
import java.io.*;
import java.lang.*;
import java.util.Scanner;





//**************************KNIGHT***************************************************************


class Knight extends ChessPiece {

    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Knight(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean isAttacking(ChessPiece other) {
        if(this.color == other.color) {
            return false;
        } else {
            //col difference is 1 and row difference is 2
            if(Math.abs(this.col - other.col) == 1 && Math.abs(this.row - other.row) == 2) {
                return true;
            //row difference is 1 and col difference is 2
            } else if(Math.abs(this.row - other.row) == 1 && Math.abs(this.col - other.col) == 2) {
                return true;
            } else {
                return false;
            }
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "N";
        } else {
            return "n";
        }
    }
}


