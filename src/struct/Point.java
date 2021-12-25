package struct;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Main;

public class Point {
    public Rectangle rect;
    Type type;
    int x;
    int y;
    public static int point_size = 20;
    public static int step = point_size;
    Color point_color = Color.GREEN;


    public Point(int a, int b){
        rect = new Rectangle();
        this.setX(a);
        this.setY(b);
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(point_size);
        rect.setHeight(point_size);
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
        rect.setX(x);
    }

    public void setY(int y) {
        this.y = y;
        rect.setY(y);
    }

    public void setType(Type type) {
        this.type = type;
        this.setColor();
    }

    public void setPoint_color(Color point_color) {
        this.point_color = point_color;
        rect.setFill(point_color);
    }

    public void setColor() {
        switch (type){
            case HEAD -> setPoint_color(Color.RED);
            case BODY -> setPoint_color(Color.GREEN);
            case TAIL -> setPoint_color(Color.BLUE);
            default -> setPoint_color(Color.BEIGE);
        }
    }

    public void show_point(){
        Main.game_root.getChildren().add(rect);
    }

    public void move_point(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public void step_up(){
        y -= step;
        if (y<0){
            y = Main.window_height - step;
        }
        rect.setY(y);
    }
    public void step_down(){
        y += step;
        if (y>Main.window_height){
            y = 0;
        }
        rect.setY(y);
    }
    public void step_left(){
        x -= step;
        if (x<0){
            x = Main.window_width - step;
        }
        rect.setX(x);
    }
    public void step_right(){

        x += step;
        if (x>Main.window_width){
            x = 0;
        }
        rect.setX(x);
    }
}
