package model.Character;

import model.DamageType;
import model.Item.Armor;
import model.Item.Weapon;

public class BasedCharacter {
    protected String name, imgpath;
    protected DamageType type;
    //add SPD status on both based status and real status
    protected Integer fullHP, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance, spd;
    protected Weapon weapon;
    protected Armor armor;

    public String getName() {
        return name;
    }

    public String getImagepath() {
        return imgpath;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getFullHp() {
        return fullHP;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getResistance() {
        return resistance;
    }

    public DamageType getType() {
        return type;
    }

    public Integer getSpd() { return spd; }

    public void equipWeapon( Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }

    public void equipArmor( Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }

    @Override
    public String toString() {
        return name;
    }
}
