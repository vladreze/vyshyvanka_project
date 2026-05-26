package Utils;

import Model.EmbroideryGrid;
import Model.GridModel;
import View.EmbroideryCanvas;
import com.sun.javafx.embed.swing.SwingFXUtilsImpl;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;

import javafx.scene.canvas.Canvas;
import javafx.stage.Window;

import javax.imageio.ImageIO;

public class FileIOManager {

    public static void saveCanvasToPng(Canvas canvas, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Canvas as PNG");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(window);

        if(file != null){
            try {
                WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, image);

                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                System.out.println("Canvas saved successfully to " + file.getAbsolutePath());

            } catch (Exception e) {
                System.out.println("Error saving canvas: " + e.getMessage());
            }
        }
    }

    public static void exportCanvas(GridModel gridModel, File file){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(gridModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GridModel importCanvas(File file){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (GridModel) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
