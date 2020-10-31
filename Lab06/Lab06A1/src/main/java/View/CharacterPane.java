package View;

import Controller.AllCustomHandler;
import Controller.Launcher;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Character.BasedCharacter;
import model.Item.Armor;
import model.Item.Weapon;

public class CharacterPane extends ScrollPane {
    private BasedCharacter character;

    public CharacterPane() {

    }

    private Pane getDetailsPane() {
        Pane characterInfoPane = new VBox(10);
        characterInfoPane.setBorder(null);
        characterInfoPane.setPadding(new Insets(25, 25, 25, 25));
        Label name, type, hp, atk, def, res, spd;
        ImageView mainImage = new ImageView();
        if (this.character != null) {
            name = new Label("Name: " + character.getName());
            mainImage.setImage(new Image(getClass().getClassLoader().getResource(character.getImagepath()).toString()));
            hp = new Label("HP: " + character.getHp().toString() + "/" + character.getFullHp().toString());
            type = new Label("Type: " + character.getType().toString());
            if(Launcher.getEquippedWeapon() != null){
                int attack = character.getPower() + ((Weapon)Launcher.getEquippedWeapon()).getPower();
                atk = new Label("ATK: " + attack);
            }else{
                atk = new Label("ATK: " + character.getPower());
            }

            if(Launcher.getEquippedArmor() != null){
                int defense =  character.getDefense() + ((Armor) Launcher.getEquippedArmor()).getDefense();
                int resistant = character.getResistance() + ((Armor) Launcher.getEquippedArmor()).getResistance();
                def = new Label("DEF: " + defense);
                res = new Label("RES: " + resistant);
            }else{
                def = new Label("DEF: " + character.getDefense());
                res = new Label("RES: " + character.getResistance());
            }
            spd = new Label("SPD: " + character.getSpd());
        } else {
            name = new Label("Name: ");
            mainImage.setImage(new Image(getClass().getClassLoader().getResource("assets/unknown.png").toString()));
            hp = new Label("HP: ");
            type = new Label("Type: ");
            atk = new Label("ATK: ");
            def = new Label("DEF: ");
            res = new Label("RES: ");
            spd = new Label("SPD: ");
        }
        Button genCharacter = new Button();
        genCharacter.setText("Generate Character");
        genCharacter.setOnAction(new AllCustomHandler.GenHeroHandler());
        characterInfoPane.getChildren().addAll(name, mainImage, type, hp, atk, def, res, spd, genCharacter);
        return characterInfoPane;
    }

    public void drawPane(BasedCharacter character) {
        this.character = character;
        Pane characterInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(characterInfo);
    }

}
