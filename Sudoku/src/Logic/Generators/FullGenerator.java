package Logic.Generators;

import Logic.Utils.RandomNumberGenerator;
import Logic.Solvers.GeneratorSolver;
import Logic.Solvers.RandomBacktrackSolver;
import Logic.Solvers.SudokuSolver;
import Logic.SudokuGame;
import Logic.Utils.Timer;

import java.util.ArrayList;

/**
 * Created by jens on 7/6/2016.
 *
 * This generator is called FullGenerator because it creates a solved puzzle
 * and removes squares 1 by 1 (and the solution remains unique)
 *
 */
public class FullGenerator implements SudokuGenerator{

    private int toRemove = 45;

    //number of removed cells
    private int removed = 0;
    private ArrayList<int[]> possibleLocations = new ArrayList<>();

    @Override
    public void generateSudoku(SudokuGame game) {
        SudokuSolver randomSolver = new RandomBacktrackSolver();
        RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getGenerator();
        GeneratorSolver generatorSolver;

        fillPossibleLocations(game);
        removed = 0;

        //create first (random) solved sudoku
        randomSolver.solveSudoku(game);

        while (removed < toRemove && possibleLocations.size() > 0) {
            //System.out.println(possibleLocations.size());
            //pick random location and remove
            int index = randomNumberGenerator.generateInteger(possibleLocations.size());
            int x = possibleLocations.get(index)[0];
            int y = possibleLocations.get(index)[1];
            possibleLocations.remove(index);
            int val = game.getSquareValue(x, y);

            game.emptySquare(x, y);

            //make copy;
            SudokuGame copy = new SudokuGame(game.getBoard().getBoardCopy());

            //solve copy with Generator Solver
            generatorSolver = new GeneratorSolver(val, x, y);
            generatorSolver.solveSudoku(copy);

            boolean solved = copy.hasWon();

            if (solved) { //solution is not unique
                game.setSquare(x, y, val);
            } else { //valid remove
                removed++;
            }
        }

        //System.out.println(removed);
    }

    private void fillPossibleLocations(SudokuGame game) {
        possibleLocations = new ArrayList<>();
        //fill possibleLocations
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                possibleLocations.add(new int[]{i, j});
            }
        }
    }
}
