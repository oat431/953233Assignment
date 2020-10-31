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
        int SPD = rand.nextInt(50) + 1; // add random speed
        if (type == 1) {
            character = new MagicalCharacter("MagicChar1", "assets/wizard.png", basedDef,basedRes,SPD);
        } else if(type == 2){
            character = new BattleMageCharacter("BattleMage", "assets/battlemage.png", basedDef,basedRes,SPD);
        } else {
            character = new PhysicalCharacter("PhysicalChar1", "assets/knight.png", basedDef,basedRes,SPD);
        }
        return character;
    }
}
