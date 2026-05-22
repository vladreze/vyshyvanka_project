package Model;

/*
 * Implementation of the GridModel interface for an embroidery application.
 * This class manages a grid of pixels, allowing for setting and retrieving pixel colors,
 * as well as clearing the grid.
 */

public class EmbroideryGrid implements GridModel{

    private final String[][] grid;
    private final int rows;
    private final int cols;

    public EmbroideryGrid(int rows, int cols) {
        this.grid = new String[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }


    // Sets the color of a specific pixel in the grid. If the position is valid, it updates the color.
    @Override
    public void setPixelColor(int row, int col, String color) {
      if(isValidPosition(row, col)){
          grid[row][col] = color;
      }
    }

    @Override
    public String getPixelColor(int row, int col) {
        if(isValidPosition(row, col)){;
            return grid[row][col];
        }
        return null;
    }

    // Clears the grid by setting all pixel colors to null.
    @Override
    public void clearGrid() {
        for(int i=0;i<rows;i++){
            for(int j=0; j<cols;j++){
                grid[i][j] = null;
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
