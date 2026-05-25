/*
 * This class creates a pop-up window that provides information about the project, including the project name, author, and description. It also includes a button to close the pop-up.
 */



package AdditionalInformation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.util.Objects;


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
        Stage helpStage = new Stage();
        helpStage.setTitle("User Manual - How to use");

        VBox contentLayout = new VBox();
        contentLayout.setPadding(new Insets(15));
        contentLayout.setAlignment(Pos.TOP_CENTER);

        Label titleLabel = new Label("Embroidery Editor: User Guide");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        contentLayout.getChildren().add(titleLabel);

        Label section1Title = new Label("1. Basic Drawing");
        section1Title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label section1Text = new Label("To start drawing, simply click on any cell in the grid. " +
                "You can select your preferred thread color from the Color Picker located on the left toolbar. " +
                "To fix a mistake, select the 'Eraser' tool and click on the colored cell to clear it.");
        section1Text.setWrapText(true);

        contentLayout.getChildren().addAll(section1Title, section1Text);

        Label section2Title = new Label("2. Magic Symmetry");
        section2Title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label section2Text = new Label("Our unique feature is real-time symmetry. " +
                "Check 'Horizontal' to mirror your stitches left-to-right. " +
                "Check 'Vertical' to mirror top-to-bottom. " +
                "Enable both for full 4-way radial symmetry, perfect for creating traditional stars and mandalas.");
        section2Text.setWrapText(true);

        contentLayout.getChildren().addAll(section2Title, section2Text);

        Label section3Title = new Label("3. Exporting Your Work");
        section3Title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label section3Text = new Label("Once your masterpiece is complete, click the 'Save (PNG)' button. " +
                "This will generate a high-quality image of your pattern without the grid lines, " +
                "ready to be printed or shared.");
        section3Text.setWrapText(true);

        contentLayout.getChildren().addAll(section3Title, section3Text, new Label(""));

        Button closeButton = new Button("Got it!");
        closeButton.setMaxWidth(150);
        closeButton.getStyleClass().add("tool-button");
        closeButton.setOnAction(c -> helpStage.close());
        contentLayout.getChildren().add(closeButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(scrollPane, 600, 500);

        try {
            String css = AdditionalButtons.class.getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("Error loading CSS: " + e.getMessage());
        }

        helpStage.setScene(scene);
        helpStage.showAndWait();




    }

    private static void addImageToLayout(VBox layout, String imagePath, double width) {
        try {
            Image img = new Image(Objects.requireNonNull(AdditionalButtons.class.getResourceAsStream(imagePath)));
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(width);
            imgView.setPreserveRatio(true);
            VBox.setMargin(imgView, new Insets(10, 0, 10, 0));
            layout.getChildren().add(imgView);
        } catch (Exception e) {
            Label placeholder = new Label("[ Image Alt: " + imagePath + " ]");
            placeholder.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
            layout.getChildren().add(placeholder);
        }
    }



}
