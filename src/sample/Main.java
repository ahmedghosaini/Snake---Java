package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import struct.Point;
import struct.Snake;

public class Main extends Application {
    final int window_width = 620;
    final  int window_height = 620;
    boolean fullscreen = false;
    public static Group root = new Group();
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

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
        start_text.setX(window_width/2 -100);
        start_text.setY(50);
        start_text.setFont(Font.font("Arial", 30));
        start_text.setFill(Color.WHITE);

        Point p = new Point(0, 0);
        p.move_forwards();
        p.show_point();

        Snake s = new Snake(200, 200);
        s.show_snake();

        root.getChildren().addAll(start_text);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
