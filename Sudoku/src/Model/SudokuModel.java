package Model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;

/**
 * Created by jens on 7/2/2016.
 */
public class SudokuModel implements Observable {

    private ArrayList<InvalidationListener> listeners = new ArrayList<>();

    private SudokuGame game = new SudokuGame();

    public SudokuModel() {

    }

    public void createGame() {
        game.createGame();
        fireInvalidationEvent();
    }

    public int getGameSize() {
        return game.getSize();
    }

    public void setSquare(int row, int col, int value) {
        game.setSquare(row, col, value);
        fireInvalidationEvent();
    }

    public void emptySquare(int row, int col) {
        game.emptySquare(row, col);
        fireInvalidationEvent();
    }

    public int getSquareValue(int row, int col) {
        return game.getSquareValue(row, col);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener list : listeners) {
            list.invalidated(this);
        }
    }
}
