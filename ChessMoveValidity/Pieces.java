
import java.io.*;
import java.lang.*;
import java.util.Scanner;






//**********************************************************************************************
//*********LINKED LIST CLASS********************************************************************
//**********************************************************************************************

class Pieces {
    ChessPiece head;
    ChessPiece whiteKing;
    ChessPiece blackKing;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public Pieces() {
        this.head = null;
        this.whiteKing = null;
        this.blackKing = null;
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
        if(this.Find(col, row) != null) {

            //comparing the current piece to the one we want to delete
            while(curr.row != row || curr.col != col) {
                prev = curr; //store the current one as the previous one
                curr = curr.next; //set the current one to the current's next

            }
            //exiting the while loop means curr.row == row && curr.col == col

            if(prev == null) { //first item is the one to delete
                this.head = curr.next;
            } else {
                prev.next = curr.next; //set the previous's next to the current's next
            }                          //this skips over the current piece, effectively deleting it

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

    

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Gets the King
    boolean GetKings() {
        ChessPiece curr = this.head;

        while(curr != null) {
            if((curr.GetType()).equals("k")) {
                this.whiteKing = curr;
            } else if((curr.GetType()).equals("K")) {
                this.blackKing = curr;
            }
            curr = curr.next;
        }


        if(this.whiteKing == null || this.blackKing == null) {
            return false;
        } else {
            return true;
        }
    }

    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Checks if the King is safe
    //clr is the color of king you wanna check for
    boolean IsKingSafe(int clr) {

        //if the color is white
        if(clr == 0) {
            if(this.whiteKing == null) {
                return false;
            }
        //else if the color is black
        } else if(clr == 1) {
            if(this.blackKing == null) {
                return false;
            }
        }


        ChessPiece curr = this.head; //get the head

        //looping through the chess board
        while(curr != null) {

            //if the color is color, then ignore it
            if(curr.color == clr) {
                ;
            //else, check it
            } else {
                int attack = 0;

                //if we are checking for the white King
                if(clr == 0) {
                    if(curr.IsAttacking(this.whiteKing) == true) {
                        attack = 1;
                    }
                //else if we are checking for the black King                    
                } else if(clr == 1) {
                    if(curr.IsAttacking(this.blackKing) == true) {
                        attack = 1;
                    }
                }

                //If piece can attack, check specs of piece
                if(attack == 1) {    

                    int[] spaces = new int[1];
                    spaces[0] = -2;

                    //if checking for White king
                    if(clr == 0) {
                        if((curr.GetType()).equals("N")) { //check for black Knight
                            return false; //King NOT safe
                        //if not a Knight
                        } else {
                            //if its any other type, get its path of attack
                            spaces = curr.GetPath(this.whiteKing.col, this.whiteKing.row);
                        }
                    //if checking for black king
                    } else if(clr == 1) {
                        if((curr.GetType()).equals("n")) { //check for white Knight
                            return false; //King NOT safe
                        //if not a Knight
                        } else {
                            //if its any other type, get its path of attack
                            spaces = curr.GetPath(this.blackKing.col, this.blackKing.row);
                        }
                    }

                    //if attacker is only one space away, white king is not safe
                    if(spaces[0] == 0) {
                        return false; //white King NOT safe
                    } else if(spaces[0] == -2) {
                        System.out.println("ERROR");
                    } 

                    boolean valid = true; //valid attack flag

                    //loop through the path
                    for(int i = 0; i < spaces.length; i+=2) {
                        //if we dont find a piece on a spot, then the spot is safe to move to
                        if(this.Find(spaces[i], spaces[i+1]) == null) {
                            ;
                        //if we find any piece on the path, move is invalid
                        } else {    
                            valid = false; //attack not valid
                            break;    
                        }
                    }

                    //if attack path is valid
                    if(valid == true) {
                        return false; //King NOT safe
                    //if something is blocking attack path, move on to next piece
                    } else {
                        ;
                    }

                }
            } 

            curr = curr.next;
        }

        //if it makes it out of the while loop, that means white king is safe
        return true;
    }


}

