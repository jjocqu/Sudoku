package Logic;

import Logic.Generators.FullGenerator;
import Logic.Generators.SudokuGenerator;
import Logic.Solvers.BacktrackSolver;
import Logic.Solvers.GeneratorSolver;
import Logic.Solvers.SudokuSolver;

import java.util.ArrayList;

/**
 * Created by jens on 7/1/2016.
 */
public class SudokuGame {

    private SudokuBoard board = new SudokuBoard();
    private RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getGenerator();
    private ArrayList<Rule> rules = new ArrayList<>();
    private int size;

    //startLocations can't be changed
    private ArrayList<int[]> startLocations = new ArrayList<>();

    public SudokuGame() {
        size = Logic.SudokuBoard.getSize();
        addRules();
    }

    public SudokuGame(SudokuBoard board) {
        size = Logic.SudokuBoard.getSize();
        addRules();
        this.board = board;

        //elements on this board are starting locations
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = board.getSquareValue(i, j);
                if (value != 0) { // startlocation
                    startLocations.add(new int[] {i, j});
                }
            }
        }
    }

    /**
     * creates a game by creating a valid random game
     * if a random game isn't valid, keep trying untill it is valid
     * is temporary: a random game doesn't always have a unique solution!!!
     */
    public void createGame() {
        boolean foundValid = false;

        while (! foundValid) {
            createRandomGame();
            foundValid = checkRules();
        }

        printBoard();
    }

    //set square and check rule
    //returns true if valid move
    public boolean setSquare(int row, int col, int value) {
        if (! isStartLocation(row, col)) {
            board.setSquare(row, col, value);
            return checkRules();
        }
        return true; //no move is 'valid'
    }

    public void emptySquare(int row, int col) {
        if (! isStartLocation(row, col)) {
            board.emptySquare(row, col);
        }
    }

    public int getSquareValue(int row, int col) {
        return board.getSquareValue(row, col);
    }

    public boolean isStartLocation(int row, int col) {
        return contains(startLocations, new int[]{row, col});
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

    public boolean hasWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getSquareValue(i, j) == 0) {
                    return false; //not completely filled in
                }
            }
        }
        return checkRules();
    }

    //generates solution with backtracksolver
    public boolean generateSolution() {
        //first clear all filled in fields by user (they could be wrong)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptySquare(i, j);
            }
        }

        //use solver
        SudokuSolver solver = new BacktrackSolver();
        return solver.solveSudoku(this);
    }

    public void generateGame() {
        //clear all previous fields
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptySquare(i, j);
            }
        }
        //generate new game
        SudokuGenerator generator = new FullGenerator();
        generator.generateSudoku(this);

        //set start locations
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = board.getSquareValue(i, j);
                if (value != 0) { // startlocation
                    startLocations.add(new int[] {i, j});
                }
            }
        }
    }

    public SudokuBoard getBoard() {
        return board;
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

        for (int[] usedCoordinate : usedCoordinates) {
            board.setSquare(usedCoordinate[0], usedCoordinate[1], randomNumberGenerator.generateInteger(size) + 1);
        }

        //save start locations
        startLocations = usedCoordinates;
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
        rules.add(new Logic.HorizontalRule());
        rules.add(new VerticalRule());
        rules.add(new SquareRule());
    }

}
