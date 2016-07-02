package Controller;

import Model.SudokuModel;
import View.SudokuView;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by jens on 7/2/2016.
 */
public class CellClickedEvent implements EventHandler<MouseEvent> {

    private final GridPane choiceMenu = new GridPane();
    private final VBox pane = new VBox();

    private int row, col;
    private SudokuModel model;
    private SudokuView source;

    public CellClickedEvent(int row, int col, SudokuModel model, SudokuView source) {
        this.row = row;
        this.col = col;
        this.model = model;
        this.source = source;

        pane.setPrefSize(100, 100);
        choiceMenu.getChildren().clear();
        int squareSize = (int) Math.sqrt(model.getGameSize());
        int counter = 1;
        for (int i = 0; i <squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                choiceMenu.add(new Label("" + counter), j, i);
                counter++;
            }
        }
        pane.getChildren().add(choiceMenu);
    }

    @Override
    public void handle(MouseEvent event) {
        int newValue = 0;
        //model.setSquare(row, col, newValue);
    }
}
