package Model;

/*
 * Interface representing the grid model for a pixel art application.
 * It defines methods for managing the grid's dimensions and pixel colors.
 */

import java.io.Serializable;

public interface GridModel extends Serializable {

    int getRows();
    int getCols();

    void setPixelColor(int row, int col, String color);
    String getPixelColor(int row, int col);

    void clearGrid();
}
