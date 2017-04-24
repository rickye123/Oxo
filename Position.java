/* This class will determine whether the user's input is valid, i.e.
whether the characters entered are correct, and whether the 
cell is already occupied */

public class Position {

    private int[] pos = new int[2]; 

    public int[] getPosition() {
        return pos; 
    }

    // check valid char and put position into array pos
    // then check to see if cell already occupied
    public boolean validPosition(String input, Display board) {

        char[][] grid = new char[3][3]; 
        Symbols symbol = Symbols.B; 

        grid = board.getGrid();

        if(invalidChar(input) == false) {
            return false; 
        }

        pos[0] = input.charAt(0) - 'a'; 
        pos[1] = input.charAt(1) - '1'; 

        if (grid[pos[0]][pos[1]] != Symbols.B.getValue()) {
            return false; 
        }
        return true; 
    }

    // check that input is valid 
    private boolean invalidChar(String input) {

        if(input.length() != 2) {
            return false; 
        }
        if (input.charAt(0) < 'a' || input.charAt(0) > 'c') {
            return false; 
        }
        if (input.charAt(1) < '1' || input.charAt(1) > '3') {
            return false; 
        }
        return true; 
    }

}