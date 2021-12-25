package struct;

import sample.Main;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Point> body = new ArrayList<>();
    int length = 3;
    int space = (int) (Point.point_width * 0.1);
    int head_x;
    int head_y;
    public Snake(int x, int y){
        this.head_x = x;
        this.head_y = y;
        Point head = new Point(head_x, head_y);
        body.add(head);
        for (int i = 0; i < length; i++) {
            Point a = new Point(head_x - i * (Point.point_width + 5) - space, head_y );
            body.add(a);
        }
    }
    public void show_snake(){
        for (Point p :
                body) {
            Main.root.getChildren().add(p.rect);
        }
    }
}
