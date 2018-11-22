
import java.io.*;
import java.lang.*;
import java.util.Scanner;






//**************************PAWN****************************************************************


class Pawn extends ChessPiece {

    ChessPiece next;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Pawn(int c, int r, int color) {
        super(c, r, color);
        this.next = null;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean isAttacking(ChessPiece other) {
        if(this.color == other.color) {
            return false;
        } else {
            //Directly in front
            if(this.col == other.col && (other.row - this.row) == 1) {
                return true;
            //Diagonal to the right
            } else if((other.col - this.col) == 1 && (other.row - this.row) == 1) {
                return true;
            //To the right
            } else if(this.row == other.row && (other.col - this.col) == 1) {
                return true;
            //Diagonal to the left
            } else if((other.col - this.col) == -1 && (other.row - this.row) == 1) {
                return true;
            //To the left
            } else if(this.row == other.row && (other.col - this.col) == -1) {
                return true;
            } else {
                return false;
            }
        }
    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        if(this.color == 1) {
            return "P";
        } else {
            return "p";
        }
    }

}

