import java.io.*;
import java.util.Scanner;

public class SudokuDriver
{
    private Sudoku sudoku; 

    public SudokuDriver() {
      sudoku = new Sudoku();
    }

    public Sudoku getSudoku() 
    {
        return this.sudoku;
    }

    public void setSudoku(Sudoku newSudoku) 
    {
        this.sudoku = newSudoku;
    }

     /* After the file has been opened, the content of the file can be read into
      the board array. Use a doubly nested loop as before.
        Each time through the loop, read the next integer from the Scanner 
        and assign the result to the current element of the board
        array. This completes the load() method. */
        public void load(String filename)  
        {
            Scanner fileReader = null;
            int[][] loadedBoard = new int[9][9]; 
            try {
                fileReader = new Scanner(new FileReader(filename));
                while(fileReader.hasNext())
                {
                    //System.out.println("Current Character:\t" + fileReader.next() );
                    for (int i = 0; i < loadedBoard.length; i++) 
                    {
                        for (int j = 0; j < loadedBoard[i].length; j++) 
                        {
                            
                            loadedBoard[i][j] = Integer.parseInt(fileReader.next());;
                        } //end inner for-loop
                    } //end outer for-loop
                    this.getSudoku().setBoard(loadedBoard);
                } //end while loop

                //fileReader.close();
            } //end try
           catch (FileNotFoundException e)
            {
                System.out.print("Error opening input file -- file not found exception");
                System.exit(-1);
            }
            catch(NullPointerException e) 
            {
                int[][] newBoard = new int[][]{ {1,2,3,5,6,7,8, 9},
                {1,2,3,5,6,7,8, 9}, {1,2,3,5,6,7,8, 9}, {1,2,3,5,6,7,8, 9},
                {1,2,3,5,6,7,8, 9}, {1,2,3,5,6,7,8, 9}, {1,2,3,5,6,7,8, 9}, {1,2,3,5,6,7,8, 9},
                {1,2,3,5,6,7,8, 9}  };
                this.getSudoku().setBoard(newBoard);
            } 
            
        }  //end load() method

    /* TODO:
            commandline() {
                create a Scanner to represent the keyboard as before
                    while (true)
                    {
                    print out a prompt “> ” to let the user know to enter a command
                    read the user’s input and store as a string. (use in.nextLine() not in.next())
                    If input is “quit”, then break
                    Else if input is “show” then call show();
                    Else if input is “load” then call load(“testfile.txt”);
                    Else tell the user the input is not recognized
                    }
                }
    */
    /* TODO: 
    The user will also want to know if the current board is correct. If the user chooses “complete” command, the
commandline method will call the isComplete method. If every row, column and subgrid is filled in with no invalid
values, then it returns true, and the commandline method prints “true” on the screen. If any values are missing or
invalid, it returns false, and the commandline method prints “false”.
    
    */
    public static void commandline() {
        Sudoku sudoku = new Sudoku();
        Scanner in = new Scanner(System.in);

        while (true) 
        {
            System.out.print("> ");
            String line = in.nextLine();
            String[] t = line.split(" "); //split line
            String cmd = t[0];

            if (cmd.equals(""))  //implement
            {

            }

            else if (cmd.equals("set"))
            {
                int r = 1;
                int c = 1;
                int v = 1 ;
                r--;
                c--;

                boolean rcheck = sudoku.checkRowConstraints(r, v);
                boolean ccheck = sudoku.checkColumnConstraints(c, v);
                boolean scheck = sudoku.checkSubgridConstraints(r, c, v);

                if (rcheck && ccheck && scheck) 
                {
                    sudoku.insert(r, c, v);
                }

                else 
                {
                    //Print error message
                }
            }
        }
    }

    public static void main(String[]args)
    {
        /*
            To run, paste in command line:
                javac SudokuDriver.java; java SudokuDriver
        */
        SudokuDriver sudokuDriver = new SudokuDriver(); 
        Scanner input = new Scanner(System.in);
        
        /* TODO:
            Your program should then display the content of the loaded file to 
            the screen. At this point, make the following simple change.
            The number 0 is a useful placeholder in the file, because it 
            represents a blank position in the grid, and is not a valid input
            value (all input values must be between 1 and 9).
        */

        /* TODO:
        Add an if-else to the inner loop of your show() method, so that if the
            value is 0, an underscore character “_” is displayed instead. 
            If the number is any other value, then just display that value.
            The resulting display of the test file will then look like this:
            
        */

        sudokuDriver.load("sudoku.txt");  
        int[][] board = sudokuDriver.getSudoku().getBoard();
        sudokuDriver.getSudoku().show(board);

        //input.close();

    } //end main() method
} //end SudokuDriver class