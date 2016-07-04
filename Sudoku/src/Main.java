import GUI.Controller.SudokuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI\\View\\sudoku.fxml"));
        primaryStage.setTitle("Sudoku game");
        SudokuController controller = new SudokuController();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();



    }



    public static void main(String[] args) {
        launch(args);
    }
}
