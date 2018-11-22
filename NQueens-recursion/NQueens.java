import java.io.*;
import java.lang.*;
import java.util.Scanner;


public class NQueens {
    
    public static void main(String[] args) throws IOException{
        
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

        //out.print("\n");
        
        //Reading the lines of input files and tokenizing them
        while(in.hasNextLine()) {
            //Take away trailing spaces, then add 1 trailing to 
            //catch blank lines
            String line = in.nextLine().trim() + " ";

            //split line around white space
            String[] token = line.split("\\s+"); //String array with all the tokens in one line
            

            boardSize = Integer.parseInt(token[0]);
            setQueenCol = Integer.parseInt(token[1]);
            setQueenRow = Integer.parseInt(token[2]);
            

            //boardSize = 4;
            //setQueenCol = 1;
            //setQueenRow = 2;

            Queen[] placedQueens = new Queen[boardSize]; //array of queens 
            
            //Adding the pre-set queen to placed queens array
            Queen setQueen = new Queen(setQueenCol, setQueenRow);
            placedQueens[setQueenCol - 1] = setQueen;

            //checking if NQueens returned true or false
            success = NQueens(boardSize, placedQueens, setQueenCol, boardSize);

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


 
    public static boolean NQueens(int numQueens, Queen[] queens, int setCol, int boardSize) {
        //
        // <<R>>
        //

        boolean moved, ret, hit;
        //BASE CASE
        if(numQueens == 0) {
            return true;
        }
        
        //Create a Queen and place it at top of column n
        Queen poss = new Queen(numQueens, 1);
        //If column n == setColumn
        //That menas the pre-set queen is placed at the current column 
        //we are trying to place a Queen in 
        //      Call NQueens(n-1, QueenArray)
        if(numQueens == setCol) {

            //RECURSION
            //go to <<R>>
            ret = NQueens(numQueens - 1, queens, setCol, boardSize); //So we move on to the next column
            return ret;
        }
        

        //infinite loop to keep on testing positions to place queens in 
        while (true) {

            //
            //<<1>>
            //
            //Detecting a hit between current Queen trying to be placed and 
            //the other placed queens
            hit = poss.hitAny(queens);

            //If queen trying to be placed hits any already placed queens
            //then we move the queen
            if(hit == true) {
                moved = poss.move(boardSize); //move the queen

                //If the move failed, that means the move is out of the board
                //This means we need to alter the position of the previous queen placed
                //So return FALSE to return back one iteration and trigger a move
                if(moved == false) {
                    return false;
                //If the move was successful, then we do nothing
                //Loops back around the infinite while to <<1>> and keeps checking for hits
                } else {
                    //do nothing
                }

            //If the queen trying to be placed does not conflict with other placed queens
            //Place that queen
            } else {
                queens[numQueens - 1] = poss; //updating the queen array

                //RECURSION
                //go to <<R>>
                ret = NQueens(numQueens - 1, queens, setCol, boardSize); //Move on to the next column

                //if the next column successfully placed a queen, then this function call was a success
                //BECAUSE:
                //1) This current place was successful and didn't collide with the other already placed queens
                //2) On top of that, the queen trying to be placed in the next column was also successfully placed,
                //   meaning that there is no need to move the current queen to accomodate for the placement of the 
                //   next queen.
                if(ret == true) {
                    //Successful Function Call
                    return true;

                //But, if the next column's queen could not be placed due to conflicts
                //That means we must move the current Queen trying to be placed
                //But since we already placed the current Queen in order to check for the next Queen,
                //we must reset that placed Queen to null
                } else { 
                    queens[numQueens - 1] = null; //resetting the Queen
                    moved = poss.move(boardSize); //and trying another position

                    //if this move is out of board,  go back and move the previous queen
                    if(moved == false) { 
                        return false; //Triggers a move on the previous Queen
                    //If this move was successfull, then loop back around to <<1>> and keep checking
                    } else {
                        //do nothing
                    }
                }
            }
        }
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
