package View;

import AdditionalInformation.AdditionalButtons;
import Controller.AppController;
import Model.GridModel;
import Utils.FileIOManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        Button exportAsPng = new Button("Export as PNG");
        exportAsPng.setMaxWidth(Double.MAX_VALUE);
        exportAsPng.getStyleClass().add("tool-button");
        exportAsPng.setOnAction(e -> {
            FileIOManager.saveCanvasToPng(canvas, canvas.getScene().getWindow());
        });
        getChildren().add(exportAsPng);

        Button exportButton = new Button("Export Project");
        exportButton.setMaxWidth(Double.MAX_VALUE);
        exportButton.getStyleClass().add("tool-button");
        exportButton.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Save Project Data");
            fileChooser.getExtensionFilters().add(
                    new javafx.stage.FileChooser.ExtensionFilter("Vyshyvka Data (*.dat)", "*.dat")
            );
            javafx.stage.Window window = exportButton.getScene().getWindow();
            java.io.File file = fileChooser.showSaveDialog(window);
            if(file != null){
                Utils.FileIOManager.exportCanvas(controller.getGridModel(), file);
            }
        });
        getChildren().add(exportButton);



        // Import Button
        Button importButton = new Button("Import Project");
        importButton.setMaxWidth(Double.MAX_VALUE);
        importButton.getStyleClass().add("tool-button");
        importButton.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Open Project");
            fileChooser.getExtensionFilters().add(
                    new javafx.stage.FileChooser.ExtensionFilter("Vyshyvka Data (*.dat)", "*.dat")
            );

            javafx.stage.Window window = importButton.getScene().getWindow();
            java.io.File file = fileChooser.showOpenDialog(window);

            if (file != null) {
                GridModel loadedGrid = FileIOManager.importCanvas(file);
                if (loadedGrid != null) {
                    controller.setGridModel(loadedGrid);
                    canvas.setGridModel(loadedGrid);
                    canvas.redraw();
                }
            }
        });
        getChildren().add(importButton);

        space(300);

        // About Button
        Button aboutButton = new Button("About");
        aboutButton.setMaxWidth(Double.MAX_VALUE);
        aboutButton.getStyleClass().add("tool-button");
        aboutButton.setOnAction(e -> {
            AdditionalButtons.displayAbout();
        });
        getChildren().add(aboutButton);


        Button helpButton = new Button("Help");
        helpButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.getStyleClass().add("tool-button");
        helpButton.setOnAction(h -> {
            AdditionalButtons.displayHelp();
        });
        getChildren().add(helpButton);

    }

    private void space(int spaceNum){
        Label space = new Label("");
        space.setMinHeight(spaceNum);
        getChildren().add(space);
    }

}
