package Model;

import java.util.ArrayList;

/**
 * Created by jens on 7/1/2016.
 */
public class VerticalRule implements Rule {

    private int size = SudokuBoard.getSize();

    @Override
    public boolean checkRegel(SudokuBoard board) {

        for (int i = 0; i < size; i++) {
            ArrayList<Integer> found = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (! (board.getSquareValue(j, i) != 0 && found.contains(board.getSquareValue(j, i)))) {
                    found.add(board.getSquareValue(j, i));
                } else { // 2 times same value in a col
                    return false;
                }
            }
        }
        return true;
    }
}
