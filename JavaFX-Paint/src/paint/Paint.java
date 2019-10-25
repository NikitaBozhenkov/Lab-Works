package paint;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class Paint extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ToggleButton pencilButton = new ToggleButton("Pencil");
        ToggleButton rubberButton = new ToggleButton("Rubber");

        ToggleButton[] toolsArray = {pencilButton, rubberButton};

        ToggleGroup toolsGroup = new ToggleGroup();

        for (ToggleButton tool : toolsArray) {
            tool.setMinWidth(50);
            tool.setToggleGroup(toolsGroup);
            tool.setCursor(Cursor.HAND);
        }

        Canvas canvas = new Canvas(1000,700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);


        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1000,700);
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
