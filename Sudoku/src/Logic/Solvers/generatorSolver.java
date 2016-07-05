package Logic.Solvers;

/**
 * Created by jens on 7/5/2016.
 *
 * This solver should only be used for building a sudoku
 * There is 1 value at (row, col) that isn't allowed (the solution)
 * When this solver finds a solution it will be a second one
 * thus the given solution won't be unique
 *
 */
public class GeneratorSolver extends BacktrackSolver {

    private int illegalValue, illegalValueX, illegalValueY;

    //the value that isn't allowed and it's location
    public GeneratorSolver(int illegalValue, int illegalValueX, int illegalValueY) {
        this.illegalValue = illegalValue;
        this.illegalValueX = illegalValueX;
        this.illegalValueY = illegalValueY;
    }

    @Override
    protected boolean backtrack(int row, int col) {

        if (row+1 == game.getSize() && col+1 == game.getSize()) { //special case: last field => no next cell
            for (int x: possibleValues) { //fill in last field
                if (isLegalValue(x, row, col)) {
                    game.setSquare(row, col, x);
                    if (game.checkRules()) {
                        return true;
                    }
                }
            }

            game.emptySquare(row, col);
            return false;
        }

        for (int x: possibleValues) { //all possible values
            if (isLegalValue(x, row, col)) {
                game.setSquare(row, col, x);
                if (game.checkRules()) {
                    int[] nextPos = nextPosition(row, col);

                    if (backtrack(nextPos[0], nextPos[1])) {
                        return true;
                    }
                }
            }
        }

        //no solution found
        game.emptySquare(row, col);
        return false;
    }

    private boolean isLegalValue(int val, int row, int col) {
        return (! (illegalValue == val && illegalValueX == row && illegalValueY == col));
    }
}
