package model.Character;

import model.DamageType;

public class MagicalCharacter extends BasedCharacter{
    public MagicalCharacter(String name, String imgpath, int baseDef, int basedRes){
        this.name = name;
        this.type = DamageType.magical;
        this.imgpath = imgpath;
        this.fullHP = 30;
        this.basedPow = 50;
        this.basedDef = baseDef;
        this.basedRes = basedRes;
        this.hp = this.fullHP;
        this.power = this.basedPow;
        this.defense = baseDef;
        this.resistance = basedRes;
    }
}
