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

    public static void displayAbout() {
        Stage popUpStage = new Stage();
        popUpStage.setTitle("About Project");

        VBox popUpLayout = new VBox();
        popUpLayout.setPadding(new Insets(15));

        Label titleLabel = new Label("Project: Pixel Embroidery Editor");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label authorLabel = new Label("Developer: Vladyslav Rezanov");
        Label institutionLabel = new Label("National University of Kyiv-Mohyla Academy");
        Label versionLabel = new Label("Version: 1.0 (2026)");

        Label descLabel = new Label("Description: A specialized JavaFX application designed for creating, editing, " +
                "and exporting traditional pixel art embroidery designs. Features include dynamic grid rendering, " +
                "multi-axis symmetry tools, color palettes, and PNG export capabilities.");
        descLabel.setWrapText(true); // Дозволяє тексту переноситися, щоб не вилізти за межі вікна
        descLabel.setMaxWidth(600);

        popUpLayout.getChildren().addAll(
                titleLabel,
                authorLabel,
                institutionLabel,
                versionLabel,
                new Label(""),
                descLabel,
                new Label("")
        );




        Button closeButton = new Button("Close");
        closeButton.setMaxWidth(150);
        closeButton.setOnAction(e -> popUpStage.close());
        closeButton.getStyleClass().add("tool-button");
        popUpLayout.getChildren().add(closeButton);

        Scene popUpScene = new Scene(popUpLayout, 650, 250);

        popUpStage.setScene(popUpScene);
        popUpStage.showAndWait();

        try {
            String css = AdditionalButtons.class.getResource("/styles.css").toExternalForm();
            popUpScene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("Error loading CSS: " + e.getMessage());
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
