package Model;

import java.util.ArrayList;

/**
 * Created by jens on 7/1/2016.
 * checks every horizontal line
 */
public class HorizontalRule implements Rule {

    private int size = SudokuBoard.getSize();

    @Override
    public boolean checkRegel(SudokuBoard board) {

        for (int i = 0; i < size; i++) {
            ArrayList<Integer> found = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (! (board.getSquareValue(i, j) != 0 && found.contains(board.getSquareValue(i, j)))) {
                    found.add(board.getSquareValue(i, j));
                } else { // 2 times same value in a row
                    return false;
                }
            }
        }

        return true;
    }
}
