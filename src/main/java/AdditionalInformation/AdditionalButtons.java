/*
 * This class creates a pop-up window that provides information about the project, including the project name, author, and description. It also includes a button to close the pop-up.
 */



package AdditionalInformation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AdditionalButtons {

    public static void displayAbout(){
        Stage popUpStage = new Stage();
        VBox popUpLayout = new VBox();
        popUpLayout.setPadding(new Insets(15));
        popUpLayout.setAlignment(Pos.CENTER);
        popUpLayout.getChildren().add(new Label("Project: Embroidery Design App"));
        popUpLayout.getChildren().add(new Label("Author: Vladyslav Rezanov"));
        popUpLayout.getChildren().add(new Label("Description: A JavaFX application for creating pixel art embroidery designs."));
        Scene popUpScene = new Scene(popUpLayout, 750, 350);
        popUpStage.setScene(popUpScene);
        popUpStage.show();


        popUpLayout.getChildren().add(new Label(""));
        popUpLayout.setMinHeight(200);
        Button closeButton = new Button("Understood");
        closeButton.setMaxWidth(100);
        closeButton.setOnAction(e -> popUpStage.close());
        closeButton.getStyleClass().add("tool-button");
        popUpLayout.getChildren().add(closeButton);


        try {
            String css = AdditionalButtons.class.getResource("/styles.css").toExternalForm();
            popUpScene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("Error loading CSS" + e.getMessage());
        }
    }

    public static void displayHelp(){
        Stage popUpStage = new Stage();
        VBox popUpLayout = new VBox();
        popUpLayout.setPadding(new Insets(15));
        popUpLayout.setAlignment(Pos.CENTER_LEFT);

        Scene popUpScene = new Scene(popUpLayout, 1000, 750);
        popUpStage.setScene(popUpScene);
        popUpStage.show();

       Button closeButton = new Button();
       closeButton.setMaxWidth(100);
       closeButton.getStyleClass().add("tool-button");
       closeButton.setOnAction(c -> {
           popUpStage.close();
       });





        try {
            String css = AdditionalButtons.class.getResource("/styles.css").toExternalForm();
            popUpScene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("Error loading CSS" + e.getMessage());
        }


    }


}
