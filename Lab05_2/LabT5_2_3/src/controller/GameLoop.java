package controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import model.Direction;
import model.Food;
import model.Snake;
import model.SpecialFood;
import view.Platforms;
import javafx.application.Platform;
public class GameLoop implements Runnable {
    private Platforms platform;
    private Snake snake;
    private Food food;
    private SpecialFood sp;
    private float interval = 1000.0f / 10;
    private boolean running;
    private int score;
    public GameLoop(Platforms platform, Snake snake, Food food){
        this.snake = snake;
        this.food = food;
        this.platform = platform;
        running = true;
        this.score = 0;
        platform.setPoint(this.score);
    }

    public void setSp(SpecialFood sp) {
        this.sp = sp;
    }

    public void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if(cur_key == KeyCode.UP && cur_direction != Direction.DOWN){
            snake.setCurrentDirection(Direction.UP);
        } else if(cur_key == KeyCode.DOWN && cur_direction != Direction.UP){
            snake.setCurrentDirection(Direction.DOWN);
        } else if(cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT){
            snake.setCurrentDirection(Direction.LEFT);
        } else if(cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT){
            snake.setCurrentDirection(Direction.RIGHT);
        }
        snake.update();
    }

    public void checkCollision() {
        if(snake.isCollidingWith(food)){
            snake.grow();
            food.respawn();
            this.score++;
            Platform.runLater(()->{
                platform.setPoint(this.score);
            });
        }

        if(snake.isDead()){
            running = false;
        }
    }

    public void eatSpecialFood(){
        if(snake.isCollidingWitSpecial(sp)){
            snake.grow();
            this.score +=5 ;
            Platform.runLater(()->{
                platform.setPoint(this.score);
            });
        }
        if(snake.isDead()){
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake,food,sp);
    }

    public Snake getSnake() {
        return snake;
    }

    public Platforms getPlatform() {
        return platform;
    }

    public int getScore() {
        return score;
    }

    public Food getFood() {
        return food;
    }

    public SpecialFood getSp() {
        return sp;
    }

    @Override
    public void run() {
        while(running) {
            update();
            checkCollision();
            eatSpecialFood();
            redraw();
            try{
                Thread.sleep((long)interval);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
       Platform.runLater(() -> {
           Label text = new Label();
           text.setText("Game Over");
           text.setStyle("-fx-font-size: 26px;");
           Popup popup = new Popup();
           popup.getContent().add(text);
           popup.show(Launcher.primaryStage);
       });
    }



}