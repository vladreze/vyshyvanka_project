

/*
 * This class serves as the controller in the MVC architecture for the pixel art application. It manages user interactions, updates the model, and handles symmetry settings.
 * It allows users to set the current color, toggle horizontal and vertical symmetry, clear the canvas, and handle click events on the grid.
 */

package Controller;

import Model.GridModel;

public class AppController {

    private GridModel gridModel;

    private String currentColor = "000000";
    private boolean horizontalSymmetry = false;
    private boolean verticalSymmetry = false;

    public AppController(GridModel gridModel) {
        this.gridModel = gridModel;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(String currentColor) {
        this.currentColor = currentColor;
    }

    public void setHorizontalSymmetry(boolean horizontalSymmetry) {
        this.horizontalSymmetry = horizontalSymmetry;
    }

    public void setVerticalSymmetry(boolean verticalSymmetry) {
        this.verticalSymmetry = verticalSymmetry;
    }

    public void clearCanvas() {
        gridModel.clearGrid();
    }

    // Handles a click event on the grid at the specified row and column. It sets the pixel color based on the current color and applies symmetry if enabled.
    public void handleClick(int row, int col){
        gridModel.setPixelColor(row, col, currentColor);

        int maxRow = gridModel.getRows() - 1;
        int maxCol = gridModel.getCols() - 1;

        // If horizontal symmetry is enabled, set the pixel color on the opposite side of the column.
        if (horizontalSymmetry) {
            gridModel.setPixelColor(row, maxCol - col, currentColor);
        }

        // If vertical symmetry is enabled, set the pixel color on the opposite side of the row.
        if (verticalSymmetry) {
            gridModel.setPixelColor(maxRow - row, col, currentColor);
        }

        // If both horizontal and vertical symmetry are enabled, set the pixel color on the diagonally opposite corner.
        if (horizontalSymmetry && verticalSymmetry) {
            gridModel.setPixelColor(maxRow - row, maxCol - col, currentColor);
        }
    }

    public void generateStartupPattern(){
        boolean prevHor = this.horizontalSymmetry;
        boolean prevVer = this.verticalSymmetry;
        String prevColor = this.currentColor;

        setVerticalSymmetry(true);
        setHorizontalSymmetry(true);
        int centerX = gridModel.getCols() / 2;
        int centerY = gridModel.getRows() / 2;
        setCurrentColor("#FF0000");

        for (int i = 1; i <= 8; i++) {
            handleClick(centerY - i, centerX - i);
        }

        for (int i = 2; i <= 6; i++) {
            handleClick(centerY - i, centerX - (8 - i));
        }
        setCurrentColor("#000000");
        for (int i = 2; i <= 10; i++) {
            handleClick(centerY, centerX - i);
            handleClick(centerY - i, centerX);
        }

        handleClick(centerY - 10, centerX - 1);
        handleClick(centerY - 10, centerX - 2);
        handleClick(centerY - 1, centerX - 10);
        handleClick(centerY - 2, centerX - 10);

        for (int i = 4; i <= 9; i++) {
            handleClick(centerY - i, centerX - i + 2);
            handleClick(centerY - i + 2, centerX - i);
        }

        setHorizontalSymmetry(prevHor);
        setVerticalSymmetry(prevVer);
        setCurrentColor(prevColor);
    }


    public GridModel getGridModel() {
        return gridModel;
    }

    public void setGridModel(GridModel gridModel) {
        this.gridModel = gridModel;
    }
}
