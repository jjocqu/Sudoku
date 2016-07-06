package Tests;

import Logic.Generators.FullGenerator;
import Logic.Generators.SudokuGenerator;
import Logic.Solvers.BacktrackSolver;
import Logic.Solvers.RandomBacktrackSolver;
import Logic.SudokuGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 7/6/2016.
 */
public class SudokuGeneratorTest {

    private SudokuGame game;

    @Before
    public void setUp() throws Exception {
        game = new SudokuGame();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFullGenerateSudoku() throws Exception {
        SudokuGenerator generator = new FullGenerator();
        BacktrackSolver solver = new BacktrackSolver();

        for (int i = 0; i < 10; i++) {
            generator.generateSudoku(game);
            assertTrue(solver.solveSudoku(game));
            assertTrue(game.hasWon());
            System.out.println();
        }
    }
}