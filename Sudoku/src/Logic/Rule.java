package Logic;

/**
 * Created by jens on 7/1/2016.
 */
public interface Rule {

    /*
     * returns true if there are no errors, else false
     */
    boolean checkRegel(SudokuBoard board);
}
