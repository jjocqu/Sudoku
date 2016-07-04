package Logic;

/**
 * Created by jens on 7/1/2016.
 *
 */
public class SudokuBoard {

    private static final int SIZE = 9;
    private SudokuField[][] board = new SudokuField[SIZE][SIZE];

    public SudokuBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    /**
     *
     * @return copy of 2D array
     */
    public SudokuField[][] getBoard() {
        return board.clone();
    }

    public void setSquare(int row, int col, int value) {
        board[row][col].setValue(value);
    }

    public void emptySquare(int row, int col) {
        board[row][col].setEmpty();
    }

    public int getSquareValue(int row, int col) {
        return board[row][col].getValue();
    }

    public static int getSize() {
        return SIZE;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < 9; i++) {
            if (i%3 == 0) {
                result += "|-----------------------------|\n";
            }
            for (int j = 0; j < 9; j++) {
                if (j%3 == 0) {
                    result += "| " + board[i][j] + " ";
                } else {
                    result += " " + board[i][j] + " ";
                }
            }
            result += "|\n";
        }

        result += "|-----------------------------|\n";


        return result;
    }
}
