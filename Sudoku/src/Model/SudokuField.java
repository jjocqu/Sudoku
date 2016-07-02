package Model;

/**
 * Created by jens on 7/1/2016.
 * This object is 1 field on the board
 * A field can be empty or not, if it's not empty, it can have a value from 1 to 9
 */
public class SudokuField {

    private boolean empty;
    private int value;

    /**
     * only possible to create empty field
     * after use setters to give values
     */
    public SudokuField() {
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty() {
        empty = true;
    }

    public int getValue() {
        if (empty) return 0;
        return value;
    }

    public void setValue(int value) {
        validValue(value);
        this.value = value;
        empty = false;
    }

    /**
     * assertion error if value is invalid
     */
    private void validValue(int value) {
        assert value < 10;
        assert value > 0;
    }

    @Override
    public String toString() {
        if (empty) {
            return "0"; // 0 stands for empty
        } else {
            return "" + value;
        }
    }
}
