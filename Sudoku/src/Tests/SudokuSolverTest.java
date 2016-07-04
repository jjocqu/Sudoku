package Tests;

import Logic.SudokuBoard;
import Logic.SudokuGame;
import Solvers.BacktrackSolver;
import Solvers.SudokuSolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 7/4/2016.
 */
public class SudokuSolverTest {

    private SudokuSolver backtrackSolver = new BacktrackSolver();

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

    }

    @Test
    public void testBacktrackSolveSudoku() throws Exception {
        boolean solved = backtrackSolver.solveSudoku(game1);
        assertTrue(solved);
        assertTrue(game1.hasWon());
        game1.printBoard();
    }
}