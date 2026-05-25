

/*
 * This class serves as the controller in the MVC architecture for the pixel art application. It manages user interactions, updates the model, and handles symmetry settings.
 * It allows users to set the current color, toggle horizontal and vertical symmetry, clear the canvas, and handle click events on the grid.
 */

package Controller;

import Model.GridModel;

public class AppController {

    private final GridModel gridModel;

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

//    public void generateStartupPattern(){
//        setVerticalSymmetry(true);
//        setVerticalSymmetry(true);
//        setCurrentColor("#FFFFFF");
//
//        int centerX = gridModel.getCols() / 2;
//        int centerY = gridModel.getRows() / 2;
//
//        handleClick(centerX, centerY);
//
//        setCurrentColor("#FF0000");
//        for(int i=0; i<5;i++){
//            handleClick(centerX, centerY - i);
//        }
//    }
}
