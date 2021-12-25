package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    final int window_width = 620;
    final  int window_height = 620;
    boolean fullscreen = false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        Image icon = new Image("/icon.png");
        primaryStage.setTitle("Snake Game");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(window_width);
        primaryStage.setHeight(window_height);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(fullscreen);
        primaryStage.show();

        Text start_text = new Text("Press Start!");
        start_text.setX(50);
        start_text.setY(50);
        start_text.setFont(Font.font("Arial", 35));
        start_text.setFill(Color.WHITE);

        root.getChildren().addAll(start_text);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
