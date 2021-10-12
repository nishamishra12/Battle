package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bag.Equipment;
import bag.EquipmentType;
import randomizer.Randomizer;
import weapon.Weapon;

public class BattlePlayer implements Player {

  private final String name;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private Map<EquipmentType, List<Equipment>> gearBag;
  private Weapon currentWeapon;
  private int health;
  private int strikingPower;
  private int avoidanceAbility;
  private int damage;

  public BattlePlayer(String name) {
    this.name = name;
    this.strength = new Randomizer().rollMyDiceFourTimes();
    this.constitution = new Randomizer().rollMyDiceFourTimes();
    this.dexterity = new Randomizer().rollMyDiceFourTimes();
    this.charisma = new Randomizer().rollMyDiceFourTimes();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getStrength() {
    return this.strength;
  }

  @Override
  public int getConstitution() {
    return this.constitution;
  }

  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  @Override
  public int getCharisma() {
    return this.charisma;
  }

  //calculate health after wearing full gear
  @Override
  public void calculateHealth() {
    for (Map.Entry<EquipmentType, List<Equipment>> entry : gearBag.entrySet()) {
      for (Equipment value : entry.getValue()) {
        for (int i = 0; i < value.getEffectAbility().size(); i++) {
          if (value.getEffectAbility().get(i).equals(Ability.CHARISMA)) {
            this.charisma += value.getEffectValue();
          } else if (value.getEffectAbility().get(i).equals(Ability.CONSTITUTION)) {
            this.constitution += value.getEffectValue();
          } else if (value.getEffectAbility().get(i).equals(Ability.DEXTERITY)) {
            this.dexterity += value.getEffectValue();
          } else if (value.getEffectAbility().get(i).equals(Ability.STRENGTH)) {
            this.strength += value.getEffectValue();
          }
        }
      }
    }
    this.health = this.charisma + this.strength + this.constitution + this.dexterity;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public Map<EquipmentType, List<Equipment>> getPlayerBag() {
    return this.gearBag;
  }

  @Override
  public Weapon getCurrentWeapon() {
    return currentWeapon;
  }

  @Override
  public void setCurrentWeapon(Weapon currentWeapon) {
    this.currentWeapon = currentWeapon;
  }

  @Override
  public void addEquipment(Equipment equipment) {
    this.gearBag = new HashMap<>();
    if (equipment.getEquipmentType().equals(EquipmentType.HEADGEAR)) {
      if (!gearBag.containsKey(EquipmentType.HEADGEAR)) {
        List<Equipment> e = new ArrayList<>();
        e.add(equipment);
        gearBag.put(equipment.getEquipmentType(), e);
      }
    } else if (equipment.getEquipmentType().equals(EquipmentType.FOOTWEAR)) {
      if (!gearBag.containsKey(EquipmentType.FOOTWEAR)) {
        List<Equipment> e = new ArrayList<>();
        e.add(equipment);
        gearBag.put(equipment.getEquipmentType(), e);
      }
    } else if (equipment.getEquipmentType().equals(EquipmentType.POTION)) {
      if (!gearBag.containsKey(EquipmentType.POTION)) {
        List<Equipment> e = new ArrayList<>();
        e.add(equipment);
        gearBag.put(equipment.getEquipmentType(), e);
      } else {
        gearBag.get(equipment.getEquipmentType()).add(equipment);
      }
    } else if (equipment.getEquipmentType().equals(EquipmentType.BELT)) {
      if (!gearBag.containsKey(EquipmentType.BELT)) {
        List<Equipment> e = new ArrayList<>();
        e.add(equipment);
        gearBag.put(equipment.getEquipmentType(), e);
      } else {
        int unitCount = 0;
        for (int i = 0; i < gearBag.get(equipment.getEquipmentType()).size(); i++) {
          unitCount = unitCount + gearBag.get(equipment.getEquipmentType()).get(i).getBeltSize();
        }
        if (equipment.getBeltSize() <= (10 - unitCount)) {
          gearBag.get(equipment.getEquipmentType()).add(equipment);
        }
      }
    }
  }
}
