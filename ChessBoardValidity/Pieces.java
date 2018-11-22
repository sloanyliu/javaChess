

import java.io.*;
import java.lang.*;
import java.util.Scanner;




//**********************************************************************************************
//*********LINKED LIST CLASS********************************************************************
//**********************************************************************************************

class Pieces {
    ChessPiece head;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Pieces() {
        this.head = null;
    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Inserts a piece at the beginnig of the list
    void Insert(ChessPiece piece) {
        piece.next = this.head; //have the inserted piece's next be the current head
        this.head = piece; //set inserted piece as the head
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Deletes the specified at the index provided
    ChessPiece Delete(int col, int row) {
        ChessPiece prev = null; //nothing in front of head
        ChessPiece curr = this.head; //start at he head

        //****
        //EMPTY LIST
        if(curr == null) {
            return null;
        }
        

        //****
        //if the piece exists, continue with deletion
        if(this.Find(col, row) == null) {

            //comparing the current piece to the one we want to delete
            while(curr.row != row || curr.col != col) {
                prev = curr; //store the current one as the previous one
                curr = curr.next; //set the current one to the current's next
            }

            //exiting the while loop means curr.row == row && curr.col == col
            
            prev.next = curr.next; //set the previous's next to the current's next
                                   //this skips over the current piece, effectively deleting it

            return curr;
        //if the piece we want to delete is not really there    
        } else {
            return null;
        }
    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Confirms existence of a piece at the provided index
    ChessPiece Find(int col, int row) {
        ChessPiece curr = this.head;

        //Empty list
        if(curr == null) {
            return null;
        }

        //move on as long as there is a next piece in the list
        while(curr != null) {
            //If we have found the piece we want
            if(curr.col == col && curr.row == row) {
                return curr; //return true
            //otherwise, continue the search
            } else {
                curr = curr.next;
            }
        }

        //making it here means either piece is not there
        return null;
    }


}

