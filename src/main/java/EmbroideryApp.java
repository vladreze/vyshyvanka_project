import Controller.AppController;
import Model.EmbroideryGrid;
import View.EmbroideryCanvas;
import View.ToolBarPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EmbroideryApp extends Application {


    @Override
    public void start(Stage stage) {

        EmbroideryGrid model = new EmbroideryGrid(30,40);
        AppController controller = new AppController(model);
        EmbroideryCanvas view = new EmbroideryCanvas(model,controller, 20);
        ToolBarPanel toolBarPanel = new ToolBarPanel(view, controller);
        BorderPane root = new BorderPane();
        root.setLeft(toolBarPanel);
        root.setCenter(view);
        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle(" APP ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
