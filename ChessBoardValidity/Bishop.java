
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
    boolean isAttacking(ChessPiece other) {
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
}

