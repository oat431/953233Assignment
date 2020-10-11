package controller;
import view.Platform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage){
        Platform platform = new Platform();
        GameLoop gameLoop = new GameLoop(platform);
        DrawingLoop drawingLoop = new DrawingLoop(platform);
        Scene scene = new Scene(platform,Platform.WIDTH,Platform.HEIGHT);
        scene.setOnKeyPressed(event -> platform.getKeys().add(event.getCode()));
        scene.setOnKeyReleased(event -> platform.getKeys().remove(event.getCode()));
        primaryStage.setTitle("platformer");
        primaryStage.setScene(scene);
        primaryStage.show();
        (new Thread(gameLoop)).start();
        (new Thread(drawingLoop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
