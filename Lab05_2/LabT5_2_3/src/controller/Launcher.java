package controller;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Food;
import model.Snake;
import model.SpecialFood;
import view.Platforms;
public class Launcher extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Launcher.primaryStage = primaryStage;
        Platforms platform = new Platforms();
        Snake snake = new Snake(new Point2D(Platforms.WIDTH/2, Platforms.HEIGHT/2));
        Food food = new Food();
        GameLoop gameLoop = new GameLoop(platform,snake,food);
        gameLoop.setSp(new SpecialFood());
        Scene scene = new Scene(platform, Platforms.WIDTH * Platforms.TILE_SIZE, Platforms.HEIGHT * Platforms.TILE_SIZE);
        scene.setOnKeyPressed(e -> platform.setKey(e.getCode()));
        scene.setOnKeyReleased(e -> platform.setKey(null));
        Launcher.primaryStage.setTitle("Snake");
        Launcher.primaryStage.setScene(scene);
        Launcher.primaryStage.setResizable(false);
        Launcher.primaryStage.show();
        (new Thread(gameLoop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
