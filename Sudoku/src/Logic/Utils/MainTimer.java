package Logic.Utils;

import Logic.Generators.FullGenerator;
import Logic.Generators.SudokuGenerator;
import Logic.Solvers.BacktrackSolver;
import Logic.Solvers.GeneratorSolver;
import Logic.Solvers.RandomBacktrackSolver;
import Logic.Solvers.SudokuSolver;
import Logic.SudokuGame;

import java.util.ArrayList;

/**
 * Created by jens on 7/7/2016.
 *
 * Class that compares the different solvers
 *
 */
public class MainTimer {

    public static void main(String[] args) {

        System.out.println("Average time to generate: " + averageTimeGenerator(new FullGenerator()) + "ms");

        System.out.println("Average time with backtrack: " + averageTimeSolver(new BacktrackSolver()) + "ms");
        System.out.println("Average time with random backtrack: " + averageTimeSolver(new RandomBacktrackSolver()) + "ms");
        System.out.println("Average time with generator : " + averageTimeSolver(new GeneratorSolver(-1, -1, -1)) + "ms");

    }

    private static long averageTimeGenerator(SudokuGenerator generator) {
        Timer timer = new Timer();
        ArrayList<Long> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            SudokuGame game = new SudokuGame();

            timer.startTimer();
            game.generateGame();
            long stop = timer.endTimer();
            if (stop > 10000) { //if it takes very long to generate
                game.printBoard();
            }
            list.add(stop);
        }

        //calculate average
        long total = 0;
        for (long tmp: list) {
            total += tmp;
        }
        return total / list.size();
    }

    private static long averageTimeSolver(SudokuSolver solver) {
        Timer timer = new Timer();
        ArrayList<Long> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            SudokuGame game = new SudokuGame();
            game.generateGame();

            timer.startTimer();
            solver.solveSudoku(game);
            long stop = timer.endTimer();
            if (stop > 10000) { //if it takes very long to solve
                game.printBoard();
            }
            list.add(stop);
        }

        //calculate average
        long total = 0;
        for (long tmp: list) {
            total += tmp;
        }
        return total / list.size();
    }

    private static void compareMethods() {
        Timer timer = new Timer();

        SudokuSolver solver;

        for (int i = 0; i < 10; i++) {
            System.out.println("sudoku " + (i+1) + ": ");
            timer.startTimer();
            SudokuGame game = createSudoku();
            System.out.println("creation time: " + timer.endTimer() + "ms");

            solver = new BacktrackSolver();
            timer.startTimer();
            solver.solveSudoku(game);
            long end = timer.endTimer();
            System.out.println("solving time (backtrack): " + end + "ms");

            game.clearGame();

            solver = new GeneratorSolver(-1, -1, -1);
            timer.startTimer();
            solver.solveSudoku(game);
            end = timer.endTimer();
            System.out.println("solving time (generator): " + end + "ms");

            game.clearGame();

            solver = new RandomBacktrackSolver();
            timer.startTimer();
            solver.solveSudoku(game);
            end = timer.endTimer();
            System.out.println("solving time (random): " + end + "ms");

            System.out.println("\n");

        }
    }

    private static SudokuGame createSudoku() {
        SudokuGenerator generator = new FullGenerator();
        SudokuGame game = new SudokuGame();

        generator.generateSudoku(game);

        return game;
    }

}
