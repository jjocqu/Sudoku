package Controller;

import Model.SudokuModel;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

/**
 * Created by jens on 7/3/2016.
 */
public class SudokuCell extends TextArea {

    private SudokuModel model;
    private int row, col;

    //always use this constructor
    public SudokuCell(String text, SudokuModel model, int row, int col) {
        super(text);
        this.model = model;
        this.row = row;
        this.col = col;
        loadEvents();
    }

    public void changeContent(String newValue) {
        setText(newValue);
    }

    //these two methodes make sure invalid input is ignored
    @Override public void replaceText(int start, int end, String text) {
        if (text.matches("[1-9]")) {
            super.replaceText(start, end, text);
        }
    }
    @Override public void replaceSelection(String text) {
        if (text.matches("[1-9]")) {
            super.replaceSelection(text);
        }
    }

    private void loadEvents() {
        //clear cell if clicked (only if it can be changed)
        setOnMouseClicked(event -> {
            if (! model.isStartLocation(row, col)) {
                setEditable(true);
                clear();
            } else {
                setEditable(false);
            }
        });

        //make sure length stays 1
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1) {
                validValueEntererd(newValue);
            } else {
                if (newValue.length() > 1) { // new valid character entered
                    validValueEntererd(newValue.substring(1));
                }
            }
        });

        //enter button disables focus (finished typing)
        setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                setFocused(false);
            }
        });
    }

    private void validValueEntererd(String newValue) {
        model.setSquare(row, col, Integer.parseInt(newValue)); //update model
        setText(newValue); //update view
    }
}
