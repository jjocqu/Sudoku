package Controller;

import Model.SudokuModel;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SudokuController {

    @FXML
    private SudokuModel model;

    @FXML
    private GridPane grid;

    public SudokuController() {
        this.model = SudokuModel.getModel();
    }

    public void initialize() {
        model.createGame();
    }

    public void newGameClicked() {
        model.createGame();
    }

    public void checkSolution() {
        Stage newStage = new Stage();
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        if (model.hasWon()) {
            label.setText("Congratulations! \n The solution is correct.");
        } else {
            label.setText("The solution is incorrect. \n Please check again.");
        }

        Scene stageScene = new Scene(pane, 300, 100);
        newStage.setScene(stageScene);
        newStage.show();
    }
}
