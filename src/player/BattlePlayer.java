package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentType;
import randomizer.RandomGenerator;
import weapon.Weapon;

public class BattlePlayer implements Player {

  private final String name;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private Map<EquipmentType, List<Equipment>> gearBag  = new HashMap<>();
  private Weapon currentWeapon;
  private int health;
  private int strikingPower;
  private int avoidanceAbility;
  private int damage;
  private int potentialDamage;
  private RandomGenerator randomGenerator;

  public BattlePlayer(String name, RandomGenerator randomGenerator) {
    this.name = name;
    this.randomGenerator = randomGenerator;
    this.strength = randomGenerator.getNextInt(6,18);
    this.constitution = randomGenerator.getNextInt(6,18);
    this.dexterity = randomGenerator.getNextInt(6,18);
    this.charisma = randomGenerator.getNextInt(6,18);
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
  public void calculateHealth() {
    updateAbility();
    this.health = this.charisma + this.strength + this.constitution + this.dexterity;
  }

  private void updateAbility() {
    for (Map.Entry<EquipmentType, List<Equipment>> entry : this.gearBag.entrySet()) {
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
  }

  @Override
  public void calculatePlayerPowers(Player player) {
    calculateStrikingPower();
    calculateAvoidanceAbility();
    calculatePotentialDamage();
    calculateActualDamage(player);
  }

  @Override
  public void removePotions() {
    System.out.println(this.gearBag);
    int sum = 0;
    if (gearBag.containsKey(EquipmentType.POTION)) {
      for (int i = 0; i < gearBag.get(EquipmentType.POTION).size(); i++) {
        sum = sum + gearBag.get(EquipmentType.POTION).get(i).getEffectValue();
      }
      this.health = this.health - sum;
      gearBag.remove(EquipmentType.POTION);
      updateAbility();
    }
  }

  private void calculateStrikingPower() {
    this.strikingPower = this.strength + randomGenerator.getNextInt(1,10);;
  }

  @Override
  public int getStrikingPower() {
    return this.strikingPower;
  }

  @Override
  public void updateHealth(int reducedHealth) {
    this.health = reducedHealth;
  }

  private void calculateAvoidanceAbility() {
    this.avoidanceAbility = this.dexterity + this.randomGenerator.getNextInt(1,6);
  }

  @Override
  public int getAvoidanceAbility() {
    return this.avoidanceAbility;
  }

  private void calculatePotentialDamage() {
    if (this.currentWeapon.getClass().getSimpleName().equals("Flail") && this.dexterity < 14
            || (this.currentWeapon.getClass().getSimpleName().equals("TwoHandedSword") && this.strength < 14)) {
      this.potentialDamage = this.strength + this.currentWeapon.getDamage() / 2;
    } else {
      this.potentialDamage = this.strength + this.currentWeapon.getDamage();
    }
  }

  @Override
  public int getPotentialDamage() {
    return this.potentialDamage;
  }

  private void calculateActualDamage(Player player) {
    this.damage = this.potentialDamage - player.getConstitution();
  }

  @Override
  public int getActualDamage() {
    return this.damage;
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
