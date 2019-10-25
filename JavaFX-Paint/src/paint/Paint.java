package paint;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Paint extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1000,700);
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);

        ToggleButton pencilButton = new ToggleButton("Pencil");
        ToggleButton rubberButton = new ToggleButton("Rubber");

        ToggleButton[] toolsArray = {pencilButton, rubberButton};

        ToggleGroup toolsGroup = new ToggleGroup();

        for (ToggleButton tool : toolsArray) {
            tool.setMinWidth(50);
            tool.setToggleGroup(toolsGroup);
            tool.setCursor(Cursor.HAND);
        }

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        Slider slider = new Slider(1, 50, 3);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);


        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(pencilButton, rubberButton, colorPicker, slider);
        buttons.setPadding(new Insets(5));
        buttons.setStyle("-fx-background-color: #999");
        buttons.setPrefWidth(130);

        Canvas canvas = new Canvas(2500,1500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);

        canvas.setOnMousePressed(e->{
            if(pencilButton.isSelected()) {
                gc.beginPath();
                gc.lineTo(e.getX(),e.getY());
            } else if (rubberButton.isSelected()) {
                //rubberButton.setCursor(Cursor.);
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

        canvas.setOnMouseEntered(event -> {
            if (pencilButton.isSelected()) {
                scene.setCursor(Cursor.HAND);
            } else if (rubberButton.isSelected()) {
                scene.setCursor(Cursor.CROSSHAIR);
            }
        });

        canvas.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });

        colorPicker.setOnHiding(event -> {
            gc.setStroke(colorPicker.getValue());
        });


        slider.valueProperty().addListener(event -> {
            gc.setLineWidth(slider.getValue());
        });



        pane.setLeft(buttons);
        pane.setCenter(canvas);

        primaryStage.show();

    }
}
