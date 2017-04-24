import java.util.*; 

/* This class creates a 3 by 3 grid and displays it to the terminal
when prompted. It also handles user input and execution of moves */

public class Display {

    private char[][] grid = new char[3][3]; 

    // display a 3 by 3 grid, with ' ' characters 
    public void setGrid(Symbols symbol) {

        for (int i = 0; i < 3; i++) {

            for(int j = 0; j < 3; j++) {
                grid[i][j] = Symbols.B.getValue();
            }

        }

    }

    public char[][] getGrid() {
        return grid; 
    }

    // displays grid with columns and numbers on the side 
    public void displayGrid() {
        System.out.println("\n    1   2   3");
        System.out.println("a   " + grid[0][0] + " | " + grid[0][1]  + " | " + grid[0][2]);
        System.out.println("   ---+---+---");
        System.out.println("b   " + grid[1][0] + " | " + grid[1][1]  + " | " + grid[1][2]);
        System.out.println("   ---+---+---");
        System.out.println("c   " + grid[2][0] + " | " + grid[2][1]  + " | " + grid[2][2] + "\n");
    }

    // adds 'X' or 'O' to grid depending on the current symbol 
    public void executeMove(Position pos, Symbols symbol) {
        int[] coordinates = new int[2]; 

        coordinates = pos.getPosition();

        int row = coordinates[0]; 
        int col = coordinates[1]; 

        if (symbol == Symbols.X) {
            grid[row][col] = Symbols.X.getValue();
        }
        else {
            grid[row][col] = Symbols.O.getValue();
        }
    }

    // asks for input from the user, continues asking until input valid 
    public void promptInput(Board game, Display board, Symbols symbol, Position pos) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Please type in a letter and a number such as b3:");
        String userInput = input.nextLine();

        while(pos.validPosition(userInput, board) == false) {
            System.out.println("Invalid move. Enter again:");
            userInput = input.nextLine();
        }

    }

}