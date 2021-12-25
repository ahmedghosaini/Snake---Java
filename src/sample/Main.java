package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import struct.Direction;
import struct.Point;
import struct.Snake;
import struct.Type;

import java.util.Random;


public class Main extends Application {
    final static public int window_width = 600;
    final  static public int window_height = 600;
    boolean fullscreen = false;
    public static Group game_root = new Group();

    double speed = 0.1;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group start_root = new Group();
        Group end_root = new Group();

        Scene start_scene = new Scene(start_root,22,22, Color.BLACK);
        Scene field_scene = new Scene(game_root, Color.BLACK);
        Scene end_scene = new Scene(end_root, Color.BLACK);



        Text start_text = new Text("Press Start!");
        start_text.setX(window_width/2 -100);
        start_text.setY(50);
        start_text.setFont(Font.font("Arial", 30));
        start_text.setFill(Color.WHITE);

        Button start_button = new Button("Start");
        start_button.setLayoutX(250);
        start_button.setLayoutY(250);
        start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(field_scene);
                primaryStage.show();
            }
        });
        start_root.getChildren().addAll(start_text, start_button);


        Image icon = new Image("/icon.png");
        primaryStage.setTitle("Snake Game");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(window_width);
        primaryStage.setHeight(window_height);
        primaryStage.setScene(start_scene);
        primaryStage.setFullScreen(fullscreen);
        primaryStage.show();



        Random random = new Random();
        int rand_x = 20 * random.nextInt(30);
        int rand_y = 20 * random.nextInt(30);
        System.out.print(rand_x + "  " +rand_y);
        Point p = new Point(rand_x, rand_y);
        p.setType(Type.GOAL);
        p.show_point();

        Snake s = new Snake(10 * 20, 0);
        s.show_snake();



        field_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP -> s.setDirection(Direction.UP);
                    case DOWN -> s.setDirection(Direction.DOWN);
                    case RIGHT -> s.setDirection(Direction.RIGHT);
                    case LEFT ->  s.setDirection(Direction.LEFT);
                }
            }
        });




        Timeline tm = new Timeline(new KeyFrame(Duration.seconds(speed), e->{
            s.move_forwards();
            if (s.head.getX() == rand_x && s.head.getY() == rand_y){
                System.out.println("Success");
            }
            if (!s.crash()){
                System.out.println("FAIL");
            }
        }));

        tm.setCycleCount(Animation.INDEFINITE);
        tm.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
