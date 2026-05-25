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

        EmbroideryGrid model = new EmbroideryGrid(39,39);
        AppController controller = new AppController(model);
        EmbroideryCanvas view = new EmbroideryCanvas(model,controller, 20);
        ToolBarPanel toolBarPanel = new ToolBarPanel(view, controller);
        BorderPane root = new BorderPane();
        root.setLeft(toolBarPanel);
        root.setCenter(view);
        Scene scene = new Scene(root, 1050, 780);


        stage.setTitle(" Vyshyvka App. made by Vladyslav Rezanov");
        stage.setScene(scene);
        stage.setResizable(false);

        controller.generateStartupPattern();
        view.redraw();
        stage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
