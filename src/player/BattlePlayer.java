package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentType;
import randomizer.RandomGenerator;
import weapon.Barehanded;
import weapon.Weapon;

/**
 * This class represents the Player present in the arena with their basic abilities,
 * battle powers, weapon, and the list of gears.
 */
public class BattlePlayer implements Player {

  private final String name;
  private final Map<EquipmentType, List<Equipment>> gearBag;
  private final RandomGenerator randomGenerator;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private Weapon currentWeapon;
  private int health;
  private int strikingPower;
  private int avoidanceAbility;
  private int damage;
  private int potentialDamage;

  /**
   * Constructs a new player with abilities, equipments, weapons using the random generator.
   *
   * @param name            this parameter takes the name of the player
   * @param randomGenerator this parameter takes the random generator
   */
  public BattlePlayer(String name, RandomGenerator randomGenerator) {

    this.name = name;
    this.randomGenerator = randomGenerator;
    this.strength = randomGenerator.getNextInt(6, 18);
    this.constitution = randomGenerator.getNextInt(6, 18);
    this.dexterity = randomGenerator.getNextInt(6, 18);
    this.charisma = randomGenerator.getNextInt(6, 18);
    this.gearBag = new HashMap<>();
    this.currentWeapon = new Barehanded();
  }

  /**
   * Constructs a defensive copy of the player with abilities, equipments,
   * weapons using the random generator.
   *
   * @param player          this parameter takes the player object
   * @param randomGenerator this parameter takes the random generator
   */
  public BattlePlayer(Player player, RandomGenerator randomGenerator) {
    this.name = player.getName();
    this.randomGenerator = randomGenerator;
    this.strength = player.getStrength();
    this.constitution = player.getConstitution();
    this.dexterity = player.getDexterity();
    this.charisma = player.getCharisma();
    this.gearBag = player.getPlayerBag();
    this.currentWeapon = player.getCurrentWeapon();
    this.health = player.getHealth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getStrength() {
    return this.strength;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getConstitution() {
    return this.constitution;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCharisma() {
    return this.charisma;
  }

  /**
   * {@inheritDoc}
   */
  public void calculateInitialHealth() {
    calculateHealthWithGears();
    this.health = this.charisma + this.strength + this.constitution + this.dexterity;
  }

  /**
   * This method calculates the health of the players after equipping with all the gears.
   */
  private void calculateHealthWithGears() {
    if (!this.gearBag.isEmpty()) {
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void calculatePlayerPowers(Player player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("Player object cannot be null");
    }
    calculateStrikingPower();
    calculateAvoidanceAbility();
    calculatePotentialDamage();
    calculateActualDamage(player);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String removeGears(int move) {
    StringBuilder stringBuilder = new StringBuilder();
    if (!gearBag.isEmpty()) {
      for (Map.Entry<EquipmentType, List<Equipment>> entry : gearBag.entrySet()) {
        List<Equipment> listOfEquipments = entry.getValue();
        for (int i = 0; i < listOfEquipments.size(); i++) {
          if (listOfEquipments.get(i).getMove() == move) {

            stringBuilder.append(updateAbilities(listOfEquipments.get(i)));
            entry.getValue().remove(listOfEquipments.get(i));
          }
        }
      }
    }
    return stringBuilder.toString();
  }

  /**
   * This method updates all the basic abilities of the player.
   *
   * @param equipment this parameter takes the equipment of the player
   */
  private String updateAbilities(Equipment equipment) {

    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < equipment.getEffectAbility().size(); i++) {
      stringBuilder.append('\n' + "Removed gear: " + equipment.getEquipmentType()
              + " for Player: " + this.name + ", Effecting Ability: "
              + equipment.getEffectAbility().get(i).abilityName() + ", Value: "
              + equipment.getEffectValue());
      this.health -= equipment.getEffectValue();
      if (equipment.getEffectAbility().get(i).equals(Ability.CHARISMA)) {
        this.charisma -= equipment.getEffectValue();
      } else if (equipment.getEffectAbility().get(i).equals(Ability.CONSTITUTION)) {
        this.constitution -= equipment.getEffectValue();
      } else if (equipment.getEffectAbility().get(i).equals(Ability.DEXTERITY)) {
        this.dexterity -= equipment.getEffectValue();
      } else if (equipment.getEffectAbility().get(i).equals(Ability.STRENGTH)) {
        this.strength -= equipment.getEffectValue();
      }
    }
    return stringBuilder.toString();
  }

  /**
   * This method calculates the striking power of the player.
   */
  private void calculateStrikingPower() {
    this.strikingPower = this.strength + randomGenerator.getNextInt(1, 10);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getStrikingPower() {
    return this.strikingPower;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateHealth(int reducedHealth) {
    this.health = reducedHealth;
  }

  /**
   * This method calculates the avoidance ability of the player.
   */
  private void calculateAvoidanceAbility() {
    this.avoidanceAbility = this.dexterity + this.randomGenerator.getNextInt(1, 6);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAvoidanceAbility() {
    return this.avoidanceAbility;
  }

  /**
   * This method calculates the potential damage of the player.
   */
  private void calculatePotentialDamage() {
    if (this.currentWeapon.getClass().getSimpleName().equals("Flail") && this.dexterity < 14
            || (this.currentWeapon.getClass().getSimpleName().equals("TwoHandedSword")
            && this.strength < 14)) {
      this.potentialDamage = this.strength + this.currentWeapon.getDamage() / 2;
    } else {
      this.potentialDamage = this.strength + this.currentWeapon.getDamage();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getPotentialDamage() {
    return this.potentialDamage;
  }

  /**
   * This method calculates the actual damage value of the player.
   *
   * @param player this parameter takes the player object
   */
  private void calculateActualDamage(Player player) {
    this.damage = this.potentialDamage - player.getConstitution();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getActualDamage() {
    return this.damage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getHealth() {
    return this.health;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<EquipmentType, List<Equipment>> getPlayerBag() {
    Map<EquipmentType, List<Equipment>> gearBagCopy =
            new HashMap<>(this.gearBag);
    return gearBagCopy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Weapon getCurrentWeapon() {
    return currentWeapon;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCurrentWeapon(Weapon currentWeapon) {
    if (currentWeapon == null) {
      throw new IllegalArgumentException("Weapon passed cannot be null");
    }
    this.currentWeapon = currentWeapon;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addEquipment(Equipment equipment) {
    if (equipment == null) {
      throw new IllegalArgumentException("Equipment passed cannot be null");
    }
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
