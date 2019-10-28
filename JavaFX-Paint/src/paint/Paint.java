package paint;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    private Scene scene;
    private Stage primaryStage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ToggleButton pencilButton;
    private ToggleButton rubberButton;
    private Button open;
    private Button save;

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        scene = new Scene(pane, 1000, 700);
        canvas = new Canvas(2500, 1500);

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(1);

        pencilButton = new ToggleButton("Pencil");
        rubberButton = new ToggleButton("Rubber");
        ToggleButton[] toolsArray = {pencilButton, rubberButton};
        ToggleGroup toolsGroup = new ToggleGroup();
        for (ToggleButton tool : toolsArray) {
            tool.setMinWidth(50);
            tool.setToggleGroup(toolsGroup);
            tool.setCursor(Cursor.HAND);
        }

        open = new Button("Open");
        save = new Button("Save");
        Button[] operationButtons = {open, save};
        for (Button button : operationButtons) {
            button.setMinWidth(90);
            button.setCursor(Cursor.HAND);
            button.setTextFill(Color.WHITE);
            button.setStyle("-fx-background-color: #666;");
            button.setStyle("-fx-background-color: #804f6d;");
        }

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(event -> graphicsContext.setStroke(colorPicker.getValue()));

        Slider slider = new Slider(1, 50, 3);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(event -> graphicsContext.setLineWidth(slider.getValue()));

        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(pencilButton, rubberButton, colorPicker, slider, open, save);
        buttons.setPadding(new Insets(5));
        buttons.setStyle("-fx-background-color: #999");
        buttons.setPrefWidth(130);

        pane.setLeft(buttons);
        pane.setCenter(canvas);

        setPaintActions();
        setOpenCloseActions();
        setCursorChanging();

        primaryStage.show();
    }

    private void setPaintActions() {
        canvas.setOnMouseDragged(e -> {
            if (pencilButton.isSelected()) {
                graphicsContext.lineTo(e.getX(), e.getY());
                graphicsContext.stroke();
            } else if (rubberButton.isSelected()) {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2,
                        e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (pencilButton.isSelected()) {
                graphicsContext.lineTo(e.getX(), e.getY());
                graphicsContext.stroke();
                graphicsContext.closePath();
            } else if (rubberButton.isSelected()) {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2,
                        e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });

        canvas.setOnMousePressed(e -> {
            if (pencilButton.isSelected()) {
                graphicsContext.beginPath();
                graphicsContext.lineTo(e.getX(), e.getY());
            } else if (rubberButton.isSelected()) {
                //rubberButton.setCursor(Cursor.);
                double lineWight = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWight / 2,
                        e.getY() - lineWight / 2, lineWight, lineWight);
            }
        });
    }

    private void setCursorChanging() {
        canvas.setOnMouseEntered(event -> setImageCursor());
        canvas.setOnMouseExited(event -> scene.setCursor(Cursor.DEFAULT));
    }

    private void setImageCursor() {
        if (pencilButton.isSelected()) {
            Image image = new Image("resource\\pencilCursor.png");
            scene.setCursor(new ImageCursor(image));
        } else if (rubberButton.isSelected()) {
            Image image = new Image("resource\\rubberCursor.png");
            scene.setCursor(new ImageCursor(image));
        }
    }

    private void setOpenCloseActions() {
        open.setOnAction((e) -> {
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    graphicsContext.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });

        save.setOnAction((e) -> {
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
    }
}
