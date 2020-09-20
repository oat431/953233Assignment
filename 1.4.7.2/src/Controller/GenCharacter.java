package Controller;

import model.Character.BasedCharacter;
import model.Character.BattleMageCharacter;
import model.Character.MagicalCharacter;
import model.Character.PhysicalCharacter;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand = new Random();
        int type = rand.nextInt(3) + 1;
        int basedDef = rand.nextInt(50) + 1;
        int basedRes = rand.nextInt(50) + 1;
        //from 1.4.7.2 I modify this part I add type 2 that will be assign to BattleMage
        if (type == 1) {
            character = new MagicalCharacter("MagicChar1", "assets/wizard.png", basedDef,basedRes);
        } else if(type == 2){
            character = new BattleMageCharacter("BattleMage", "assets/battlemage.png", basedDef,basedRes);
        } else {
            character = new PhysicalCharacter("PhysicalChar1","assets/knight.png", basedDef,basedRes);
        }
        return character;
    }
}
