package Controller;

import model.DamageType;
import model.Item.Armor;
import model.Item.BasedEquipment;
import model.Item.Weapon;

import java.util.ArrayList;

public class GenItemList {

    public static ArrayList<BasedEquipment> setUpItemList() {
        ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(5);
        itemLists.add(new Weapon("Sword", 10, DamageType.physical, "assets/sword.png"));
        itemLists.add(new Weapon("Gun", 20, DamageType.physical, "assets/gun.png"));
        itemLists.add(new Weapon("Staff", 30, DamageType.magical, "assets/staff.png"));
        itemLists.add(new Armor("shirt", 0, 50, "assets/shirt.png"));
        itemLists.add(new Armor("armor", 50, 0, "assets/armor.png"));
        //This is 1.4.7.1 add one more item in the itemLists
        itemLists.add(new Armor("suit",60,10,"assets/suit.png"));
        itemLists.add(new Weapon("Bow",30,DamageType.magical,"assets/bow.png"));
        return itemLists;
    }
}
