package Model;

import java.util.ArrayList;

/**
 * Created by jens on 7/1/2016.
 */
public class SudokuGame {

    private SudokuBoard board = new SudokuBoard();
    private RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getGenerator();
    private ArrayList<Rule> rules = new ArrayList<>();
    private int size;

    public SudokuGame() {
        size = SudokuBoard.getSize();
        addRules();
    }

    /**
     * creates a game by creating a valid random game
     * if a random game isn't valid, keep trying untill it is valid
     */
    public void createGame() {
        boolean foundValid = false;

        while (! foundValid) {
            createRandomGame();
            foundValid = checkRules();
        }

        printBoard();
    }

    public void setSquare(int row, int col, int value) {
        board.setSquare(row, col, value);
    }

    public void emptySquare(int row, int col) {
        board.emptySquare(row, col);
    }

    public int getSquareValue(int row, int col) {
        return board.getSquareValue(row, col);
    }

    public void printBoard() {
        System.out.println(board);
    }

    public int getSize() {
        return size;
    }

    //returns true if valid board
    public boolean checkRules() {
        for (Rule rule: rules) {
            if (! rule.checkRegel(board)) { //problem with a rule
                return false;
            }
        }
        return true;
    }

    /*
     * minimum is number of needed squares is 17
     * we choose 25 random squares
     */
    private void createRandomGame() {
        ArrayList<int[]> usedCoordinates = new ArrayList<>();

        // make sure the board is empty
        emptyBoard();

        //find different coordinates
        while (usedCoordinates.size() < 25) {
            int[] temp = new int[]{randomNumberGenerator.generateInteger(size), randomNumberGenerator.generateInteger(size)};

            // default contains(arraylist) doesn't work because it compares hashcodes
            if (! contains(usedCoordinates, temp)) {
                usedCoordinates.add(temp);
            }
        }

        for (int i = 0; i < usedCoordinates.size(); i++) {
            board.setSquare(usedCoordinates.get(i)[0], usedCoordinates.get(i)[1], randomNumberGenerator.generateInteger(size)+1);
        }
    }

    //returns true if list contains array
    private boolean contains(ArrayList<int[]> list, int[] element) {
        for (int[] temp : list) {
            if (temp[0] == element[0] && temp[1] == element[1]) {
                return true;
            }
        }
        return false;
    }

    private void emptyBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.emptySquare(i, j);
            }
        }
    }

    private void addRules() {
        rules.add(new HorizontalRule());
        rules.add(new VerticalRule());
        rules.add(new SquareRule());
    }

}
