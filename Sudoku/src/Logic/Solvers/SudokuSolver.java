package Logic.Solvers;

import Logic.SudokuGame;

/**
 * Created by jens on 7/4/2016.
 */
public interface SudokuSolver {

    /*
     * returns true is sudoku is solved, false if it can't be solved
     */
    boolean solveSudoku(SudokuGame game);

}
