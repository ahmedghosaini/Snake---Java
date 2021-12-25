package struct;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Main;

public class Point {
    public Rectangle rect;
    //Shape body;
    Type type;
    Direction direction;
    int x;
    int y;
    public static int point_width = 15;
    int point_height = 15;
    Color point_color = Color.GREEN;
    int step = 25;
    public Point(int a, int b){
        rect = new Rectangle();
        x = a;
        y = b;
        setDirection(Direction.RIGHT);
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(point_width);
        rect.setHeight(point_height);
        rect.setFill(point_color);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void show_point(){
        Main.root.getChildren().add(rect);
    }

    public void move_point(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    public void move_forwards(){
        switch (this.direction) {
            case UP -> y -= step;
            case DOWN -> y += step;
            case LEFT -> x -= step;
            case RIGHT -> x += step;
        }
        rect.setX(x);
        rect.setY(y);
    }
}
