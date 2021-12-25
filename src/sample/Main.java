package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

    final static public int width = 600;
    final  static public int height = 620;
    final static public int window_width = 600;
    final  static public int window_height = 600;
    boolean fullscreen = false;
    public static Group game_root = new Group();
    Random random = new Random();
    public static int dimension = 20;
    public static int positions = window_height / dimension;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group start_root = new Group();
        //Group end_root = new Group();

        Scene start_scene = new Scene(start_root, Color.BLACK);
        Scene field_scene = new Scene(game_root, window_height, window_height);
        field_scene.setFill(Color.BLACK);


        Button restart_button = new Button("Start again");
        restart_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(field_scene);
            }
        });





        Text start_text = new Text("Press Start!");
        start_text.setX(250);
        start_text.setY(50);
        start_text.setFont(Font.font("Arial", 15));
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


        //Primary Stage
        Image icon = new Image("/icon.png");
        primaryStage.setTitle("Snake Game");
        primaryStage.getIcons().add(icon);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setScene(start_scene);
        primaryStage.setFullScreen(fullscreen);
        primaryStage.show();

        //Snake
        Snake snake = new Snake(10 * 20, 0);
        double speed = 0.1;

        //Apple
        int rand_x = dimension * random.nextInt(positions-1);
        int rand_y = dimension * random.nextInt(positions-1);
        Point apple = new Point(rand_x, rand_y);
        apple.setType(Type.GOAL);
        apple.show_point();

        //Keys
        field_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP -> snake.setDirection(Direction.UP);
                    case DOWN -> snake.setDirection(Direction.DOWN);
                    case RIGHT -> snake.setDirection(Direction.RIGHT);
                    case LEFT ->  snake.setDirection(Direction.LEFT);
                }
            }
        });

        BorderPane end_root = new BorderPane();
        Scene end_scene = new Scene(end_root);
        Label score_text = new Label("Score : " + snake.getScore().toString());
        //Timeline
        Timeline tm = new Timeline(new KeyFrame(Duration.seconds(speed), e->{
            snake.move_forwards();
            score_text.setText("Score : " + snake.getScore());
            if (snake.head.getX() == apple.getX() && snake.head.getY() == apple.getY()){
                System.out.println("Success");
                snake.increment_length();
                int rand_x1 = dimension * random.nextInt(positions-1) ;
                int rand_y1 = dimension * random.nextInt(positions-1) ;
                apple.move_point(rand_x1, rand_y1);
                //System.out.println(snake.getScore());

            }
            if (!snake.check_collision()){
                System.out.println("Fail");
                snake.stop();
                primaryStage.setScene(end_scene);
            }
            System.out.print(score_text.getText());
        }));


        VBox box1 = new VBox(50);
        box1.getChildren().add(restart_button);
        box1.setAlignment(Pos.CENTER);
        Label score_text1 = new Label();
        score_text1.setText("You Lost! Try Again.");
        score_text1.setFont(Font.font("Arial", 30));
        score_text1.setTextFill(Color.WHITE);
        box1.getChildren().add(score_text1);
        end_root.setTop(box1);
        end_root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        game_root.getChildren().add(score_text);
        snake.show_snake();
        tm.setCycleCount(Animation.INDEFINITE);
        tm.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
