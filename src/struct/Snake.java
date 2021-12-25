package struct;

import sample.Main;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public List<Point> body;
    int length = 3;
    public Point head;
    int head_x;
    int head_y;
    Direction direction;
    public boolean running = true;


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
        build_body(length);
        head = body.get(0);
        head_x = head.getX();
        head_y = head.getY();
    }

    public void build_body(int length){
        List<Point> b= new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Point a = new Point(head_x - i * Main.dimension, head_y );
            if (i==0) a.setType(Type.HEAD);
            else if (i== length - 1) a.setType(Type.TAIL);
            else a.setType(Type.BODY);
            b.add(a);
        }
        this.body = b;
    }
    public void show_snake(){
        for (Point p :
                body) {
            p.show_point();
        }
    }

    public void increment_length(){
        body.get(length-1).setType(Type.BODY);
        this.length ++;
        Point a = new Point(head_x + Main.dimension, head_y );
        body.add(a);
        body.get(length-1).setType(Type.TAIL);
        body.get(length-1).show_point();
    }

    public void printSize(){
        System.out.println(this.body.size());
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
    public boolean check_collision(){
        for (int i = 1; i < length; i++) {
            int first_x = body.get(i).getX();
            int first_y = body.get(i).getY();
            int second_x = body.get(0).getX();
            int second_y = body.get(0).getY();
            if (first_x == second_x && first_y == second_y){
                return false;
            }
        }
        return true;
    }
    public void stop(){
        this.running = false;
    }

    public Integer getScore() {
        return length-3;
    }
}
