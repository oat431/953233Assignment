package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import model.Character.BasedCharacter;
import model.DamageType;
import model.Item.Armor;
import model.Item.BasedEquipment;
import model.Item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenHeroHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.setEquippedWeapon(null);
            Launcher.setEquippedArmor(null);
            Launcher.refreshPane();
        }
    }

    public static class UnequippedWeapon implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            Launcher.setEquippedWeapon(null); // set to null
            Launcher.setAllEquipments(GenItemList.setUpItemList()); // generate the item again
            Launcher.refreshPane(); // refresh the all the pane
        }
    }

    public static class UnequippedArmor implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            Launcher.setEquippedArmor(null);
            Launcher.setAllEquipments(GenItemList.setUpItemList());
            Launcher.refreshPane();
        }
    }

    public static class UnequippedAll implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            Launcher.setEquippedWeapon(null);
            Launcher.setEquippedArmor(null);
            Launcher.setAllEquipments(GenItemList.setUpItemList());
            Launcher.refreshPane();
        }
    }

    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            event.acceptTransferModes(TransferMode.MOVE);
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharacter character = Launcher.getMainCharacter();
            /*add the last request in 1.4.7.6*/
            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                if(((Weapon)retrievedEquipment).getDamageType() == DamageType.physical && (character.getType() == DamageType.magical)){
                    Launcher.setAllEquipments(GenItemList.setUpItemList());
                    Launcher.refreshPane();
                    return ;
                }
                if(((Weapon)retrievedEquipment).getDamageType() == DamageType.magical && (character.getType() == DamageType.physical)){
                    Launcher.setAllEquipments(GenItemList.setUpItemList());
                    Launcher.refreshPane();
                    return ;
                }
                if (Launcher.getEquippedWeapon() != null) {
                    allEquipments.add(Launcher.getEquippedWeapon());
                }
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                character.equipWeapon((Weapon) retrievedEquipment);
            } else {
                if(character.getType() == DamageType.both){
                    Launcher.setAllEquipments(GenItemList.setUpItemList());
                    Launcher.refreshPane();
                    return ;
                }
                if (Launcher.getEquippedArmor() != null) {
                    allEquipments.add(Launcher.getEquippedArmor());
                }
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
            }
            /*end the 1.4.7.6*/
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }

    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(
                BasedEquipment.DATA_FORMAT);
        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();
    }

}
