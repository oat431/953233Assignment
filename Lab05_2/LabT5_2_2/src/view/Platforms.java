package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Food;
import model.Snake;

public class Platforms extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    public Label score;
    private Canvas canvas;
    private KeyCode key;

    public Platforms(){
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        score = new Label("0");
        score.setTranslateX(28);
        score.setTranslateY(28);
        score.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        this.getChildren().add(canvas);
        this.getChildren().add(score);
    }

    public void render(Snake snake, Food food){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH * TILE_SIZE,HEIGHT * TILE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE,TILE_SIZE);
        });
        gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public KeyCode getKey(){
        return key;
    }

    public void setKey(KeyCode key){
        this.key = key;
    }

    public void setPoint(int score){
        this.score.setText(Integer.toString(score));
    }
}
