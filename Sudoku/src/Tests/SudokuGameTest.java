package Tests;

import Logic.SudokuGame;

import static org.junit.Assert.*;

/**
 * Created by jens on 7/4/2016.
 */
public class SudokuGameTest {

    //game 1 and 2 must be valid boards
    //game 3 and 4 invalid
    private SudokuGame game1 = new SudokuGame();
    private SudokuGame game2 = new SudokuGame();
    private SudokuGame game3 = new SudokuGame();
    private SudokuGame game4 = new SudokuGame();

    private int[][] validBoard1 = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };
    private int[][] validBoard2 = {
            {8, 3, 5, 4, 1, 6, 9, 2, 7},
            {2, 9, 6, 8, 5, 7, 4, 3, 1},
            {4, 1, 7, 2, 9, 3, 6, 5, 8},
            {5, 6, 9, 1, 3, 4, 7, 8, 2},
            {1, 2, 3, 6, 7, 8, 5, 4, 9},
            {7, 4, 8, 5, 2, 9, 1, 6, 3},
            {6, 5, 2, 7, 8, 1, 3, 9, 4},
            {9, 8, 1, 3, 4, 5, 2, 7, 6},
            {3, 7, 4, 9, 6, 2, 8, 1, 5}
    };
    private int[][] invalidBoard1 = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 1}
    };
    private int[][] invalidBoard2 = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 0}
    };

    @org.junit.Before
    public void setUp() throws Exception {
        for (int i = 0; i < game1.getSize(); i++) {
            for (int j = 0; j < game1.getSize(); j++) {
                game1.setSquare(i, j, validBoard1[i][j]);
                game2.setSquare(i, j, validBoard2[i][j]);
                game3.setSquare(i, j, invalidBoard1[i][j]);
                if (i == 8 && j == 8) {
                    //0 is invalid
                } else {
                    game4.setSquare(i, j, invalidBoard2[i][j]);
                }
            }
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testHasWon() throws Exception {
        assertTrue(game1.hasWon());
        assertTrue(game2.hasWon());
        assertFalse(game3.hasWon());
        assertFalse(game4.hasWon());
    }
}