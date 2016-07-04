package Solvers;

import Logic.SudokuGame;

/**
 * Created by jens on 7/4/2016.
 */
public class BacktrackSolver implements SudokuSolver{

    private SudokuGame game;

    @Override
    public boolean solveSudoku(SudokuGame game) {

        this.game = game;

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

    private boolean backtrack(int row, int col) {

        if (row+1 == game.getSize() && col+1 == game.getSize()) {
            return true; //all cells filled in
        }

        for (int x = 1; x < game.getSize()+1; x++) { //all possible values
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
    private int[] nextPosition(int row, int col) {
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
