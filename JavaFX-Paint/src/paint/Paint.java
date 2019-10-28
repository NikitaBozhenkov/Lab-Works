package paint;

import com.sun.javafx.css.converters.CursorConverter;
import com.sun.javafx.cursor.CursorFrame;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        Button open = new Button("Open");
        Button save = new Button("Save");

        Button[] operationButtons = {open, save};

        for (Button button : operationButtons) {
            button.setMinWidth(90);
            button.setCursor(Cursor.HAND);
            button.setTextFill(Color.WHITE);
            button.setStyle("-fx-background-color: #666;");
            button.setStyle("-fx-background-color: #804f6d;");
        }

        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(pencilButton, rubberButton, colorPicker, slider, open, save);
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
                Image image = new Image("resource\\pencilCursor.png");
                scene.setCursor(new ImageCursor(image));
            } else if (rubberButton.isSelected()) {
                Image image = new Image("resource\\rubberCursor.png");
                scene.setCursor(new ImageCursor(image));
            }
        });

        canvas.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });

        colorPicker.setOnAction(event -> {
            gc.setStroke(colorPicker.getValue());
        });


        slider.valueProperty().addListener(event -> {
            gc.setLineWidth(slider.getValue());
        });

        open.setOnAction((e)->{
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    gc.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });

        save.setOnAction((e)->{
            FileChooser saveFile = new FileChooser();
            saveFile.setTitle("Save File");

            File file = saveFile.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(2500, 1500);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });



        pane.setLeft(buttons);
        pane.setCenter(canvas);

        primaryStage.show();

    }
}
