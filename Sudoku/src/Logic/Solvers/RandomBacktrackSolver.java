package Logic.Solvers;

import Logic.RandomNumberGenerator;

import java.util.Arrays;

/**
 * Created by jens on 7/5/2016.
 *
 * A variation on the 'normal' backtrack solver
 * Instead of filling in (1..9) we now first shuffle these elements
 *
 */
public class RandomBacktrackSolver extends BacktrackSolver {

    @Override
    protected void loadPossibleValues() {
        super.loadPossibleValues(); //initialize list normally
        shuffle(possibleValues); //shuffle the list
    }

    //Fisher-Yates shuffle
    private void shuffle(int[] arr) {
        RandomNumberGenerator generator = RandomNumberGenerator.getGenerator();

        for (int i = arr.length-1; i > 0; i--) {
            int index = generator.generateInteger(i+1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
