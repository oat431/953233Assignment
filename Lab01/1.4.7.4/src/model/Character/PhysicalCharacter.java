package model.Character;

import model.DamageType;

public class PhysicalCharacter extends BasedCharacter {
    public PhysicalCharacter(String name, String imgpath, int baseDef, int basedRes, int speed) {
        this.name = name;
        this.type = DamageType.physical;
        this.imgpath = imgpath;
        this.fullHP = 50;
        this.basedPow = 30;
        this.basedDef = baseDef;
        this.basedRes = basedRes;
        this.hp = this.fullHP;
        this.power = this.basedPow;
        this.defense = baseDef;
        this.resistance = basedRes;
        this.spd = speed;
    }
}
