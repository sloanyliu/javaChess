
import java.io.*;
import java.lang.*;
import java.util.Scanner;





//**********************************************************************************************
//******************CHESS PIECE CLASSES********************************************************
//**********************************************************************************************


class ChessPiece {
    //Variable accessible by this class
    int col;
    int row;
    int color; //0 is white
               //1 is black
    ChessPiece next;
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public ChessPiece(int c, int r, int color) {
        this.col = c; //storing the column
        this.row = r; //storing the row
        this.color = color; //storing the color
        this.next = null; //the next object
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    boolean isAttacking(ChessPiece other) {
        return false;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String GetType() {
        return "";
    }

}


