package View;

import Controller.SudokuCell;
import Model.SudokuModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Created by jens on 7/2/2016.
 */
public class SudokuView extends GridPane implements InvalidationListener {

    private SudokuModel model;

    //array of all subgridpanes
    private GridPane[][] gridpanes = new GridPane[3][3];

    //array of all SudokuCell objects
    private SudokuCell[][] cells = new SudokuCell[9][9];

    //creates in each grid a new 3*3 subgridpane
    public SudokuView() {

        this.model = SudokuModel.getModel();
        this.model.addListener(this);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane subGridPane = new GridPane();
                subGridPane.getStyleClass().add("bigSquare");
                add(subGridPane, j, i);
                gridpanes[i][j] = subGridPane;
            }
        }

        initializeBoard();
    }

    @Override
    public void invalidated(Observable observable) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = model.getSquareValue(i, j);

                cells[i][j].changeContent(String.valueOf(value));
            }
        }
    }

    //initialize empty board
    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int gridPaneX = (int) Math.floor(i/3);
                int gridPaneY = (int) Math.floor(j/3);
                int subGridPaneX = i%3;
                int subGridPaneY = j%3;
                GridPane subGrid = gridpanes[gridPaneX][gridPaneY];

                SudokuCell num = new SudokuCell("", model, i, j);
                num.setPrefSize(50, 50);
                num.getStyleClass().add("square");


                subGrid.add(num, subGridPaneY, subGridPaneX);
                cells[i][j] = num;
            }
        }
    }
}
