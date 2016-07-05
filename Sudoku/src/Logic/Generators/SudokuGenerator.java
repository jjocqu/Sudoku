package Logic.Generators;

import Logic.SudokuGame;

/**
 * Created by jens on 7/5/2016.
 */
public interface SudokuGenerator {

    //builds a new sudoku to solve
    void generateSudoku(SudokuGame game);

}
