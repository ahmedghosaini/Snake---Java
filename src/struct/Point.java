package struct;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Main;

public class Point {
    public Rectangle rect;
    Type type;
    int x;
    int y;
    int point_size = Main.dimension;
    int step = Main.dimension;
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
            case HEAD -> setPoint_color(Color.GREENYELLOW);
            case BODY -> setPoint_color(Color.GREEN);
            case TAIL -> setPoint_color(Color.DARKGREEN);
            default -> setPoint_color(Color.YELLOW);
        }
    }

    public void show_point(){
        Main.game_root.getChildren().add(rect);
    }

    public void move_point(int x, int y){
        if (y>Main.window_height-40){
            y = 0;
        }else
            this.setY(y);
        if (x>Main.window_width-40){
            x = 0;
        }else
            this.setX(x);
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
        if (y>Main.window_height-40){
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
        if (x>Main.window_width-40){
            x = 0;
        }
        rect.setX(x);
    }
}
