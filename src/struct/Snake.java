package struct;

import sample.Main;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Point> body = new ArrayList<>();
    final int length = 8;
    int space = (int) (0);
    public Point head;
    int head_x;
    int head_y;
    Direction direction;

    public void setDirection(Direction direction) {
        if(!(this.direction == Direction.LEFT && direction == Direction.RIGHT || this.direction == Direction.RIGHT && direction == Direction.LEFT)){
            if (!(this.direction == Direction.UP && direction == Direction.DOWN || this.direction == Direction.DOWN && direction == Direction.UP))
                this.direction = direction;
        }
    }

    public Snake(int x, int y){
        this.head_x = x;
        this.head_y = y;
        setDirection(Direction.RIGHT);
        //Point head = new Point(head_x, head_y);
        //body.add(head);
        for (int i = 0; i < length; i++) {
            Point a = new Point(head_x - i * Point.point_size, head_y );
            switch (i){
                case 0 -> a.setType(Type.HEAD);
                case (length - 1 ) -> a.setType(Type.TAIL);
                default -> a.setType(Type.BODY);
            }
            body.add(a);
        }
        head = body.get(0);
        head_x = head.getX();
        head_y = head.getY();
    }
    public void show_snake(){
        for (Point p :
                body) {
            p.show_point();
        }
    }
    public List<Point> copy_move(List<Point> move){
        List<Point> new_move = new ArrayList<>();
        for (Point p :
                move) {
            Point new_p = new Point(p.getX(), p.getY());
            new_move.add(new_p);
        }
        return new_move;
    }
    public void move_forwards(){
        List<List<Point>> moves = new ArrayList<>();
        List<Point> previous_move = copy_move(body);
        for (int i = 0; i < length; i++) {
            moves.add(previous_move);
            if(i == 0){
                switch (this.direction) {
                    case UP -> head.step_up();
                    case DOWN -> head.step_down();
                    case LEFT -> head.step_left();
                    case RIGHT -> head.step_right();
                }
            }else{
                int new_x = moves.get(i-1).get(i-1).getX();
                int new_y = moves.get(i-1).get(i-1).getY();
                body.get(i).move_point(new_x, new_y);
            }
            previous_move = copy_move(body);
        }
    }
    public boolean crash(){
        for (int i = 0; i < length; i++) {
            int first_x = body.get(i).getX();
            int first_y = body.get(i).getY();
            for (int j = 0; j < length; j++) {
                int second_x = body.get(j).getX();
                int second_y = body.get(j).getY();
                if (first_x == second_x && first_y == second_y && i != j){
                    return false;
                }
            }
        }
        return true;
    }
}
