package model.Character;

import model.DamageType;
public class BattleMageCharacter extends BasedCharacter{
    public BattleMageCharacter(String name, String imgpath, int baseDef, int basedRes,int speed){
        this.name = name;
        this.type = DamageType.both;
        this.imgpath = imgpath;
        this.fullHP = 40;
        this.basedPow = 40;
        this.basedDef = baseDef;
        this.basedRes = basedRes;
        this.hp = this.fullHP;
        this.power = this.basedPow;
        this.defense = baseDef;
        this.resistance = basedRes;
        this.spd = speed;
    }
}
