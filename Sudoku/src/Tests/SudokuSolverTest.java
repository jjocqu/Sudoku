package Tests;

import Logic.Solvers.GeneratorSolver;
import Logic.Solvers.RandomBacktrackSolver;
import Logic.SudokuBoard;
import Logic.SudokuGame;
import Logic.Solvers.BacktrackSolver;
import Logic.Solvers.SudokuSolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 7/4/2016.
 */
public class SudokuSolverTest {

    private BacktrackSolver backtrackSolver;

    private SudokuGame game1;

    private int[][] board1 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    @Before
    public void setUp() throws Exception {
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < SudokuBoard.getSize(); i++) {
            for (int j = 0; j < SudokuBoard.getSize(); j++) {
                if (board1[i][j] != 0) {
                    board.setSquare(i, j, board1[i][j]);
                }
            }
        }
        game1 = new SudokuGame(board);
    }

    @After
    public void tearDown() throws Exception {
        for (int i = 0; i < game1.getSize(); i++) {
            for (int j = 0; j < game1.getSize(); j++) {
                game1.emptySquare(i, j); //empty the sudoku
            }
        }
    }

    @Test
    public void testBacktrackSolveSudoku() throws Exception {
        backtrackSolver = new BacktrackSolver();
        boolean solved = backtrackSolver.solveSudoku(game1);

        assertTrue(solved);
        assertTrue(game1.hasWon());

        game1.printBoard();
    }

    @Test
    public void testRandomBacktrackSolveSudoku() throws Exception {
        backtrackSolver = new RandomBacktrackSolver();
        boolean solved = backtrackSolver.solveSudoku(game1);

        assertTrue(solved);
        assertTrue(game1.hasWon());

        game1.printBoard();
    }

    @Test
    public void testGeneratorSolveSudoku() throws Exception {
        backtrackSolver = new GeneratorSolver(1, 8, 6);
        boolean solved = backtrackSolver.solveSudoku(game1);

        //this sudoku has a unique solution so it must be impossible to find another one
        assertFalse(solved);
        assertFalse(game1.hasWon());

        game1.printBoard();
    }
}