package paint;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Paint extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1000,700);
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
