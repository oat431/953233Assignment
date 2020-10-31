package model;

import javafx.geometry.Point2D;
import view.Platforms;

import java.util.Random;

public class Food {
    private Point2D position;
    private Random rn;

    public Food(Point2D position){
        this.rn = new Random();
        this.position = position;
    }

    public Food(){
        this.rn = new Random();
        respawn();
    }

    public void respawn() {
        Point2D prev_position = this.position;
        do{
            this.position = new Point2D(rn.nextInt(Platforms.WIDTH),rn.nextInt(Platforms.HEIGHT));
        }while(prev_position == this.position);
    }

    public Point2D getPosition() {
        return position;
    }
}
