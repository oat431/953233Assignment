package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Character;
import model.Keys;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Image platformImg;
    private Character character;
    private Character character2;
    private Keys keys;

    public Platform(){
        keys = new Keys();
        this.platformImg = new Image(getClass().getResourceAsStream("/assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character = new Character(30,30,0,0,KeyCode.A,KeyCode.D,KeyCode.W);
        character2 = new Character(60,30,0,0,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP);
        this.getChildren().addAll(backgroundImg,character,character2);
    }

    public Character getCharacter(){
        return character;
    }

    public Character getCharacter2() {
        return character2;
    }

    public Keys getKeys() { return keys; }
}
