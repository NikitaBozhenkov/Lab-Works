package paint;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(pencilButton, rubberButton);
        buttons.setPadding(new Insets(5));
        buttons.setStyle("-fx-background-color: #999");
        buttons.setPrefWidth(100);

        Canvas canvas = new Canvas(1000,700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);

        canvas.setOnMousePressed(e->{
            if(pencilButton.isSelected()) {
                gc.beginPath();
                gc.lineTo(e.getX(),e.getY());
            } else if (rubberButton.isSelected()) {
                double lineWight = gc.getLineWidth();
                gc.clearRect(e.getX() - lineWight /2, e.getY() - lineWight/2, lineWight, lineWight);
            }
        });

        canvas.setOnMouseDragged(e->{
            if(pencilButton.isSelected()) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            }
            else if(rubberButton.isSelected()){
                double lineWidth = gc.getLineWidth();
                gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });

        canvas.setOnMouseReleased(e-> {
                    if (pencilButton.isSelected()) {
                        gc.lineTo(e.getX(), e.getY());
                        gc.stroke();
                        gc.closePath();
                    } else if (rubberButton.isSelected()) {
                        double lineWidth = gc.getLineWidth();
                        gc.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
                    }
                });


        BorderPane pane = new BorderPane();
        pane.setLeft(buttons);
        pane.setCenter(canvas);

        Scene scene = new Scene(pane, 1000,700);
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
