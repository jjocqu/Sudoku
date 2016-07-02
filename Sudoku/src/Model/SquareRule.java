package Model;

import java.util.ArrayList;

/**
 * Created by jens on 7/1/2016.
 */
public class SquareRule implements Rule {

    private int size = SudokuBoard.getSize();
    private int squareSize = (int) Math.sqrt(size);


    @Override
    public boolean checkRegel(SudokuBoard board) {

        for (int i = 0; i < size; i+=squareSize) {
            for (int j = 0; j < size; j+=squareSize) { //these 2 loop iterate over every square
                ArrayList<Integer> found = new ArrayList<>();
                for (int offsetI = 0; offsetI < squareSize; offsetI++) {
                    for (int offsetJ = 0; offsetJ < squareSize; offsetJ++) { //these 2 loops iterate over 1 square
                        if (! (board.getSquareValue(i+offsetI, j+offsetJ) != 0 && found.contains(board.getSquareValue(i+offsetI, j+offsetJ)))) {
                            found.add(board.getSquareValue(i+offsetI, j+offsetJ));
                        } else { // 2 times same value in a square
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
