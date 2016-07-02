package Controller;

import Model.SudokuModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private SudokuModel model;

    @FXML
    private GridPane grid;

    public SudokuController(SudokuModel model) {
        this.model = model;
    }

    public void initialize() {

    }

    public void newGameClicked() {
        model.createGame();
    }
}
