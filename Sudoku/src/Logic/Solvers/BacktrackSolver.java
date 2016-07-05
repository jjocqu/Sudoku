package Logic.Solvers;

import Logic.SudokuGame;

/**
 * Created by jens on 7/4/2016.
 *
 * This solver uses a backtracking algorithm
 *
 */
public class BacktrackSolver implements SudokuSolver{

    protected SudokuGame game;
    protected int[] possibleValues;

    @Override
    public boolean solveSudoku(SudokuGame game) {
        this.game = game;
        loadPossibleValues();

        //find first empty square to start backtrack
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                if (game.getSquareValue(i, j) == 0) {
                    return backtrack(i, j); //start backtrack
                }
            }
        }
        return game.checkRules(); //game completely filled in
    }

    //fill in possible values
    protected void loadPossibleValues() {
        possibleValues = new int[game.getSize()];
        for (int i = 0; i < game.getSize(); i++) {
            possibleValues[i] = i+1;
        }
    }

    protected boolean backtrack(int row, int col) {

        if (row+1 == game.getSize() && col+1 == game.getSize()) { //special case: last field => no next cell
            for (int x: possibleValues) { //fill in last field
                game.setSquare(row, col, x);
                if (game.checkRules()) {
                    return true;
                }
            }

            game.emptySquare(row, col);
            return false;
        }

        for (int x: possibleValues) { //all possible values
            game.setSquare(row, col, x);
            if (game.checkRules()) {
                int[] nextPos = nextPosition(row, col);

                if (backtrack(nextPos[0], nextPos[1])) {
                    return true;
                }
            }
        }

        //no solution found
        game.emptySquare(row, col);
        return false;
    }

    //calculate next position (none starting position)
    protected int[] nextPosition(int row, int col) {
        boolean notFound = true;

        while (notFound) {
            if (row == game.getSize() - 1 && col == game.getSize() - 1) {
                break; //no increment
            }

            if (col == game.getSize() - 1) { //new row
                row++;
                col = 0;
            } else {
                col++;
            }

            notFound = game.isStartLocation(row, col);
        }

        return new int[] {row, col};
    }
}
