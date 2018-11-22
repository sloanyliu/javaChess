
/* Sloan Liu || 11/21/18 || NQueens.java

This is the Stack implementation of the NQueens problem

The program is able to take more than one pre-set Queen and fill the board 

If the placement is a success, then the program will print all the positions of the queens that are placed

If the placement is not successful, then the program will print No Solution
*/


import java.io.*;
import java.lang.*;
import java.util.Scanner;
import java.util.Stack;



public class NQueens {
    
    public static void main(String[] args) throws IOException {

        int boardSize, setQueenCol, setQueenRow;
        boolean success = false;

        //Making sure there is input file and output file
        if(args.length < 2) {
            System.out.println("Usage: java -jar NQueens.jar <input file> <output file>");
            System.exit(1);
        }

        //Opening the files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));


        //Reading the lines of input files and tokenizing them
        while(in.hasNextLine()) {
            //Take away trailing spaces, then add 1 trailing to 
            //catch blank lines
            String line = in.nextLine().trim() + " ";

            //split line around white space
            String[] token = line.split("\\s+"); //String array with all the tokens in one line
            
            int n = token.length; //getting the length of the token

            boardSize = Integer.parseInt(token[0]); //getting the size of the board from the line

            Queen[] placedQueens = new Queen[boardSize]; //array of queens 

            //Adding the pre-set queens to placed queens array
            for(int j = 1; j < n; j+=2) {
                setQueenCol = Integer.parseInt(token[j]); //pre-set column
                setQueenRow = Integer.parseInt(token[j+1]); //pre-set row
            
                Queen setQueen = new Queen(setQueenCol, setQueenRow);
                placedQueens[setQueenCol - 1] = setQueen;
            }

            //checking if NQueens returned true or false
            success = NQueens(placedQueens, boardSize);

            if(success == false) {
                //print no solution and new line
                out.print("No solution");
                out.print("\n");
            } else if(success == true) {
                //Print the array
                for(int j = 0; j < boardSize; j++) {
                    out.print(placedQueens[j].column + " " + placedQueens[j].row + " ");
                }
                
                out.print("\n");
            }
        }

        //closing time
        in.close();
        out.close();

    }


 
    //Stack Implementation
    public static boolean NQueens(Queen[] placedQueens, int boardSize) {
        
        Stack<Queen[]> queens = new Stack<>(); //making a new Stack of Queen[]

        Queen[] letsGo = new Queen[boardSize]; //Creating a Queen[] to push to the Stack
        //Copying the contents of placedQueens into the new array
        for(int h = 0; h < boardSize; h++) {
            letsGo[h] = placedQueens[h];
        }

        queens.push(letsGo); //push the new array to start the Stack
        
        //This loop does not end until the stack is empty
        while(queens.empty() == false) {
            //Pop the head of the stack in order to start checking where we can place queens
            Queen[] popped = queens.pop();

            //Gettin the number of Queens already placed to check the base case
            int placed = 0;
            for(int d = 0; d < boardSize; d++) {
                if(popped[d] != null) { //not null means there is a Queen there
                    placed++;
                }
            }


            //BASE CASE
            //If the popped array is the same length as the boardsize
            //that means there is a Queen array with all the positions filled
            if(placed == boardSize) {
                //Copy that array into the parameter to save the Queen positions
                for(int m = 0; m < boardSize; m++) {
                    placedQueens[m] = popped[m];
                }
                return true; //SUCCESS
            }


            //If Base case did not pass
            //Find an empty column to move on
            int emptyCol = 0;
            for(int i = 0; i < boardSize; i++) {
                if(popped[i] == null) { //null means there is nothing there
                    emptyCol = i + 1;
                    break;
                }
            }

            //Create a queen that can be possibly placed at the top of the empty column
            Queen poss = new Queen(emptyCol, 1);

            //Checker vairable for the move function
            boolean keepMoving = true;
            
            //As long as we can keep moving the piece, that means there could be more 
            //possibilities we have not tried
            while(keepMoving == true) {
                //If the possible Queen hits any of the pre-existing queens
                //that means the position is not valid
                if(poss.hitAny(popped) == true) {
                    keepMoving = poss.move(boardSize); //move the queen

                //If the possible Queen does not hit any of the pre-existing queens
                } else {

                    //Make a new Queen array and copy popped into it so I can push it later
                    Queen[] newPoss = new Queen[boardSize];
                    for(int p = 0; p < boardSize; p++) {
                        newPoss[p] = popped[p];
                    }

                    newPoss[emptyCol-1] = new Queen(poss.column, poss.row); //put the new queen in the copy

                    queens.push(newPoss); //push it to the stack

                    keepMoving = poss.move(boardSize); //move the queen for a new possibility
                }
            }
        }
        //If Stack is empty and there has not been a return, that means there is no solution
        return false; //FAILURE
    }

}





class Queen {

    int column;
    int row;


    public Queen(int c, int r) {
        this.column = c;
        this.row = r;
    }


    //Goes through the whole array and checks for hits
    //returns true if this queen hits any of the queens
    //returns false if this queen doesn't hit any queen
    boolean hitAny(Queen[] queens) {
        for(int i = 0; i < queens.length; i++) {
            //If Queen is present in current index, and it hits other queens
            if((queens[i] != null) && this.isHit(queens[i]) == true){
                return true;
            }
        }

        //if we exit the for loop, it means we did not hit any placed queens
        return false;
    }

    //returns true if two queens hit
    //returns false otherwise
    boolean isHit(Queen q) {
        //Horizontal and vertical check
        if(this.column == q.column || this.row == q.row) {
            return true;
        //Diagonal check
        } else if(Math.abs(this.column - q.column) == Math.abs(this.row - q.row)) {
            return true;
        } else {
            return false;
        }
    }

    //moving the queen down one row
    //returns true if move is successful
    //returns false if move is out of bounds
    boolean move(int size) {
        // row++; //move down 1

        if(size < this.row + 1) {
            return false;
        } else {
            this.row++;
            return true;
        }
    }



}
