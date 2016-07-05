package GUI.Model;

import Logic.SudokuGame;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;

/**
 * Created by jens on 7/2/2016.
 *
 * This class is a singleton
 *
 */
public class SudokuModel implements Observable {

    private static final SudokuModel model = new SudokuModel();

    private ArrayList<InvalidationListener> listeners = new ArrayList<>();

    private SudokuGame game = new SudokuGame();

    private SudokuModel() {

    }

    public void createGame() {
        game.createGame();
        fireInvalidationEvent();
    }

    public boolean generateSolution() {
        boolean found = game.generateSolution();
        fireInvalidationEvent();
        return found;
    }

    public void generateGame() {
        game.generateGame();
    }

    public int getGameSize() {
        return game.getSize();
    }

    public boolean setSquare(int row, int col, int value) {
        boolean valid = game.setSquare(row, col, value);
        fireInvalidationEvent();
        //for debugging
        //game.printBoard();
        return valid;
    }

    public void emptySquare(int row, int col) {
        game.emptySquare(row, col);
        fireInvalidationEvent();
    }

    public boolean hasWon() {
        return game.hasWon();
    }

    public boolean isStartLocation(int row, int col) {
        return game.isStartLocation(row, col);
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

    public static SudokuModel getModel() {
        return model;
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener list : listeners) {
            list.invalidated(this);
        }
    }
}
