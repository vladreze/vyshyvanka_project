package View;

import AdditionalInformation.AboutProject;
import Controller.AppController;
import Utils.FileIOManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ToolBarPanel extends VBox{
    private final EmbroideryCanvas canvas;
    private final AppController controller;

    public ToolBarPanel(EmbroideryCanvas canvas, AppController controller) {
        this.canvas = canvas;
        this.controller = controller;
        setSpacing(10);
        setPadding(new Insets(10));
        this.getStyleClass().add("toolbar-panel");

        initUI();
    }

    private void initUI() {

        // Color Palette
        getChildren().add(new Label("Color Palette"));
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setMaxWidth(Double.MAX_VALUE);
        colorPicker.setOnAction(c ->{
            Color fxcolor = colorPicker.getValue();
            String hexColor = String.format("%02X%02X%02X",
                    (int) (fxcolor.getRed() * 255),
                    (int) (fxcolor.getGreen() * 255),
                    (int) (fxcolor.getBlue() * 255));
            controller.setCurrentColor(hexColor);
        });
        getChildren().add(colorPicker);

        getChildren().add(new Label("Toolbar"));

        // Erase Button
        Button eraseButton = new Button("Erase");
        eraseButton.setMaxWidth(Double.MAX_VALUE);
        eraseButton.getStyleClass().add("tool-button");
        eraseButton.setOnAction(e -> { controller.setCurrentColor(null);
        });
        getChildren().add(eraseButton);

        // Clear All Button
        Button clearAllButton = new Button("Clear All");
        clearAllButton.setMaxWidth(Double.MAX_VALUE);
        clearAllButton.getStyleClass().add("tool-button");
        clearAllButton.setOnAction(c ->{
            controller.clearCanvas();
            canvas.redraw();
        });
        getChildren().add(clearAllButton);

        getChildren().add(new Label("Symmetry:"));
        // Horizontal Symmetry Checkbox
        CheckBox horizontalSymmetryCheckBox = new CheckBox("Horizontal");
        horizontalSymmetryCheckBox.setOnAction(h -> controller.setHorizontalSymmetry(horizontalSymmetryCheckBox.isSelected()));
        getChildren().add(horizontalSymmetryCheckBox);

        // Vertical Symmetry Checkbox
        CheckBox verticalSymmetryCheckBox = new CheckBox("Vertical");
        verticalSymmetryCheckBox.setOnAction(v -> controller.setVerticalSymmetry(verticalSymmetryCheckBox.isSelected()));
        getChildren().add(verticalSymmetryCheckBox);

        // Export Button
        Button exportButton = new Button("Export as PNG");
        exportButton.setMaxWidth(Double.MAX_VALUE);
        exportButton.getStyleClass().add("tool-button");
        exportButton.setOnAction(e -> {
            FileIOManager.saveCanvasToPng(canvas, canvas.getScene().getWindow());
        });
        getChildren().add(exportButton);

        Label space = new Label("");
        space.setMinHeight(200);
        getChildren().add(space);

        // About Button
        Button aboutButton = new Button("About");
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.getStyleClass().add("tool-button");
        aboutButton.setOnAction(e -> {
            AboutProject.displayInfo();
        });
        getChildren().add(aboutButton);



    }
}
