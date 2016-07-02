package View;

import Controller.CellClickedEvent;
import Model.SudokuModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by jens on 7/2/2016.
 */
public class SudokuView extends GridPane implements InvalidationListener {

    private SudokuModel model;

    //array of all subgridpanes
    private GridPane[][] gridpanes = new GridPane[3][3];

    //creates in each grid a new 3*3 subgridpane
    public SudokuView() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane subGridPane = new GridPane();
                subGridPane.getStyleClass().add("bigSquare");
                add(subGridPane, j, i);
                gridpanes[i][j] = subGridPane;
            }
        }
    }

    public SudokuModel getModel() {
        return model;
    }

    public void setModel(SudokuModel newModel) {
        if (newModel != model) {
            if (model != null) {
                model.removeListener(this);
            }
            model = newModel;
            if (model != null) {
                model.addListener(this);
            }
        }
    }

    @Override
    public void invalidated(Observable observable) {
        //clear all previous subgrids
        for (GridPane[] temp : gridpanes) {
            for (GridPane subGrid : temp) {
                subGrid.getChildren().clear();
            }
        }

        for (int i = 0; i < model.getGameSize(); i++) {
            for (int j = 0; j < model.getGameSize(); j++) {
                int value = model.getSquareValue(i, j);
                int gridPaneX = (int) Math.floor(i/3);
                int gridPaneY = (int) Math.floor(j/3);
                int subGridPaneX = i%3;
                int subGridPaneY = j%3;
                GridPane subGrid = gridpanes[gridPaneX][gridPaneY];
                Label label = new Label("" + value);
                label.setPrefSize(50, 50);
                label.setAlignment(Pos.CENTER);
                label.getStyleClass().add("square");
                label.setOnMouseClicked(new CellClickedEvent(i, j, model, this));
                subGrid.add(label, subGridPaneY, subGridPaneX);
            }
        }
    }
}
