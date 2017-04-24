import java.util.*;

/* This class controls the rest of the classes. It has the main method 
as well as the method for playing the game noughts and crosses. */

public class Oxo {

    public static void main(String[] args) {

        boolean testing = false; 
        assert(testing = true); 

        Oxo noughtsAndCrosses = new Oxo();
        Board game = new Board();
        Symbols symbol = Symbols.X; // start player is X

        if(testing) {
            game.testing();
        }
        else if (args.length == 0) {
            noughtsAndCrosses.playGame(game, symbol);
        }
        else {
            System.err.println("Use:");
            System.err.println("    java -ea Oxo for testing or");
            System.err.println("    java Oxo to play");
            System.exit(1);
        }

    }
    
    // set grid, display it, and loop until there is a winner 
    // or until there are no more possible moves 
    private void playGame(Board game, Symbols symbol) {

        Display board = new Display(); 
        Position pos = new Position();

        board.setGrid(symbol); 
        board.displayGrid();

        char turn = Symbols.X.getValue();
        boolean gameOver = false; 

        while (gameOver != true) {
            game.playerTurn(turn);
            symbol = game.symbolTurn(turn, symbol);
            System.out.println(" " + "(" + symbol + ")");
            board.promptInput(game, board, symbol, pos);
            board.executeMove(pos, symbol); 
            board.displayGrid(); 
            if(game.gameFinished(board, game, turn) == true) {
                gameOver = true; 
            }

            turn = game.switchTurn(turn); 
        }
    }

}
