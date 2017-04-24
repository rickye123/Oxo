/* This class will check whose turn it is and determine 
whether there is a winner */

public class Board {

    private Symbols symbol; 
    // accepts the symbol as its turn and returns whether
    // it is player 1 or player 2's turn
    public void playerTurn(char turn) {

        try {
            if(turn == Symbols.X.getValue()) {
                System.out.print("It's player 1's turn ");
            }
            else if (turn == Symbols.O.getValue()) {
                System.out.print("It's player 2's turn ");
            }
        } 
        catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }

    }

    // determines who is the winner based on whose turn it is
    private void winner(char turn) {

        try {
            if(turn == Symbols.X.getValue()) {
                System.out.println("Player 1 Wins");
            }
            else if(turn == Symbols.O.getValue()) {
                System.out.println("Player 2 Wins");
            }
        }
        catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }

    // swithces from 'X' to 'O' or from 'O' to 'X'
    public char switchTurn(char turn) {

        try {
            if(turn == Symbols.X.getValue()) {
                turn = Symbols.O.getValue(); 
            }
            else if (turn == Symbols.O.getValue()) {
                turn = Symbols.X.getValue(); 
            }
        }
        catch (Exception e) {
        	System.out.println("Exception caught: " + e);
        }

        return turn; 
    }

    // returns the symbol to determine whether 'O' or 'X' will
    // be printed on next turn 
    public Symbols symbolTurn(char turn, Symbols symbol) {
 
        try {
            if (turn == Symbols.X.getValue()) {
                symbol = Symbols.X;
            }
            else if (turn == Symbols.O.getValue()) {
                symbol = Symbols.O;
            }
        }
        catch (Exception e) {
        	System.out.println("Exception caught at " + e);
        }

        return symbol; 
    }
    
    // tests to see whether the game has been won or drawn (no spaces left)
    public boolean gameFinished(Display board, Board game, char turn) {

        char[][] grid = new char[3][3]; 

        grid = board.getGrid();

        if(someoneWon(grid) == true) {
        	game.winner(turn); 
        	return true; 
        }
        if(noMoreSpaces(grid) == true) {
            System.out.println("No more possible moves. Game Over!");
        	return true; 
        }
        return false; 
    }

    // cycles through grid and if there are ' ' characters present 
    // then there are still moves to be mades 
    private boolean noMoreSpaces(char[][] grid) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(grid[i][j] == Symbols.B.getValue()) {
                    return false; 
                }
            }
        }
        return true; 
    }

    // looks at each winning combo and if the cells match then someone
    // has won
    private boolean someoneWon(char[][] grid) {

        if (line(grid[0][0], grid[0][1], grid[0][2])) return true;
        if (line(grid[1][0], grid[1][1], grid[1][2])) return true;
        if (line(grid[2][0], grid[2][1], grid[2][2])) return true;
        if (line(grid[0][0], grid[1][0], grid[2][0])) return true;
        if (line(grid[0][1], grid[1][1], grid[2][1])) return true;
        if (line(grid[0][2], grid[1][2], grid[2][2])) return true;
        if (line(grid[0][0], grid[1][1], grid[2][2])) return true;
        if (line(grid[2][0], grid[1][1], grid[0][2])) return true;
        return false; 

    }

    // if characters match and are not equal to ' ' then there is a 
    // winning line 
    private boolean line(char x, char y, char z) {
        return x == y && y == z && z != Symbols.B.getValue();        
    }

    // tests performed on functions 
    public void testing() {

        positionTests(); 
        switchTurnTests();
        symbolTurnTests();
        someoneWonTests();
        lineTests();
        noMoreSpacesTests();
        System.out.println("All Tests Passed");

    }

    // tests on noMoreSpaces function
    void noMoreSpacesTests() {

        // spaces left 
        char[][] testGrid = { {'X', 'X', 'X'}, {' ', 'O', ' '}, {' ', 'O', ' '} };
        assert(noMoreSpaces(testGrid) == false);
        testGrid = new char[][] { {' ', 'O', ' '}, {'X', 'X', 'X'}, {' ', 'O', ' '} };
        assert(noMoreSpaces(testGrid) == false);
        testGrid = new char[][] { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
        assert(noMoreSpaces(testGrid) == false);

        // no more spaces 
        testGrid = new char[][] { {'X', 'O', 'X'}, {'X', 'O', 'O'}, {'O', 'X', 'O'} };
        assert(noMoreSpaces(testGrid) == true);
        testGrid = new char[][] { {'O', 'O', 'X'}, {'X', 'X', 'O'}, {'O', 'X', 'X'} };
        assert(noMoreSpaces(testGrid) == true);

    }

    // tests on winning line function 
    void lineTests() {
        assert(line('X', 'X', 'X') == true); 
        assert(line('O', 'O', 'O') == true);
        assert(line('X', 'O', 'O') == false);
        assert(line('O', 'X', 'O') == false);
        assert(line('O', 'O', 'X') == false);
        assert(line(' ', ' ', ' ') == false);
        assert(line('X', ' ', ' ') == false);
        assert(line('O', 'O', ' ') == false);
    }

    // tests on whether someone has won function 
    void someoneWonTests() {

        // winning line 
        char[][] testGrid = { {'X', 'X', 'X'}, {' ', 'O', ' '}, {' ', 'O', ' '} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {' ', 'O', ' '}, {'X', 'X', 'X'}, {' ', 'O', ' '} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {' ', 'O', ' '}, {' ', 'O', ' '}, {'X', 'X', 'X'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {'O', ' ', ' '}, {'O', 'X', ' '}, {'O', ' ', 'X'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {' ', 'O', ' '}, {'X', 'O', ' '}, {' ', 'O', 'X'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {' ', 'O', ' '}, {'X', 'O', ' '}, {' ', 'O', 'X'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {' ', ' ', 'O'}, {'X', ' ', 'O'}, {' ', ' ', 'O'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {'X', ' ', 'O'}, {' ', 'X', 'O'}, {' ', ' ', 'X'} };
        assert(someoneWon(testGrid) == true);
        testGrid = new char[][] { {'X', ' ', 'O'}, {' ', 'O', 'X'}, {'O', ' ', ' '} };
        assert(someoneWon(testGrid) == true);

        // no winning line
        testGrid = new char[][] { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
        assert(someoneWon(testGrid) == false);
        testGrid = new char[][] { {'O', ' ', 'X'}, {'X', 'X', 'O'}, {'O', 'X', ' '} };
        assert(someoneWon(testGrid) == false);
        testGrid = new char[][] { {'X', 'O', 'X'}, {'X', 'O', 'O'}, {'O', 'X', 'O'} };
        assert(someoneWon(testGrid) == false);
        testGrid = new char[][] { {'O', 'O', 'X'}, {'X', 'X', 'O'}, {'O', 'X', 'X'} };
        assert(someoneWon(testGrid) == false);

    }

    // tests on what current symbol is 
    void symbolTurnTests() {

        char turn = Symbols.X.getValue();

        assert(symbolTurn(turn, symbol) == Symbols.X);
        turn = Symbols.O.getValue(); 
        assert(symbolTurn(turn, symbol) == Symbols.O);
        turn = Symbols.X.getValue(); 
        assert(symbolTurn(turn, symbol) == Symbols.X);
    }

    // tests on switching symbols from 'O' to 'X' or 'X' to 'O'
    void switchTurnTests() {

        char turn = Symbols.X.getValue(); 
        assert(switchTurn(turn) == 'O');
        turn = Symbols.O.getValue(); 
        assert(switchTurn(turn) == 'X');
    }

    // tests the validPosition and executeMove functions 
    void positionTests() {

        Position testPosition = new Position();
        Display testBoard = new Display(); 
        Symbols testSymbol = Symbols.X;

        testBoard.setGrid(testSymbol); 

        int[] testCoordinates = new int[2]; 

        // valid test cases 
        assert(testPosition.validPosition("a1", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 0);
        assert(testCoordinates[1] == 0);
        assert(testPosition.validPosition("a2", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 0);
        assert(testCoordinates[1] == 1);
        assert(testPosition.validPosition("a3", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 0);
        assert(testCoordinates[1] == 2);
        assert(testPosition.validPosition("b1", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 1);
        assert(testCoordinates[1] == 0);
        assert(testPosition.validPosition("b2", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 1);
        assert(testCoordinates[1] == 1);
        assert(testPosition.validPosition("b3", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 1);
        assert(testCoordinates[1] == 2);
        assert(testPosition.validPosition("c1", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 2);
        assert(testCoordinates[1] == 0);
        assert(testPosition.validPosition("c2", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 2);
        assert(testCoordinates[1] == 1);
        assert(testPosition.validPosition("c3", testBoard) == true);
        testCoordinates = testPosition.getPosition(); 
        assert(testCoordinates[0] == 2);
        assert(testCoordinates[1] == 2);

        // invalid test cases 
        assert(testPosition.validPosition("d2", testBoard) == false);
        assert(testPosition.validPosition("b0", testBoard) == false);
        assert(testPosition.validPosition("b4", testBoard) == false);
        assert(testPosition.validPosition("2b", testBoard) == false);
        assert(testPosition.validPosition("b2x", testBoard) == false);
        assert(testPosition.validPosition("b", testBoard) == false);
        assert(testPosition.validPosition("", testBoard) == false);

        // a3 executed, resulting in position being invalid
        assert(testPosition.validPosition("a3", testBoard) == true);
        testBoard.executeMove(testPosition, testSymbol);
        assert(testPosition.validPosition("a3", testBoard) == false); 

    }

}