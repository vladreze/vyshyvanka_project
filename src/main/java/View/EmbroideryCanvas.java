package View;

import Controller.AppController;
import Model.GridModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EmbroideryCanvas extends Canvas {

    private final GridModel model;
    private final AppController controller;
    private final int cellSize; // Розмір однієї клітинки на екрані (в пікселях)

    public EmbroideryCanvas(GridModel model, AppController controller, int cellSize) {
        super(model.getCols() * cellSize, model.getRows() * cellSize);
        this.model = model;
        this.controller = controller;
        this.cellSize = cellSize;

        setupMouseEvents();
        redraw();
    }

    private void setupMouseEvents() {
        this.setOnMouseClicked(event -> handleMouse(event.getX(), event.getY()));
        this.setOnMouseDragged(event -> handleMouse(event.getX(), event.getY()));
    }

    private void handleMouse(double x, double y) {
        int col = (int) (x / cellSize);
        int row = (int) (y / cellSize);
        controller.handleClick(row, col);
        redraw();
    }

    public void redraw() {

        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < model.getRows(); row++) {
            for (int col = 0; col < model.getCols(); col++) {
                String hexColor = model.getPixelColor(row, col);

                int x = col * cellSize;
                int y = row * cellSize;

                if (hexColor != null) {
                    gc.setFill(Color.web(hexColor));
                    gc.fillRect(x, y, cellSize, cellSize);
                }

                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(1.0);
                gc.strokeRect(x, y, cellSize, cellSize);
            }
        }

    }

}
