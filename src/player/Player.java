package player;

import java.util.List;
import java.util.Map;

import equipment.Equipment;
import equipment.EquipmentType;
import weapon.Weapon;

/**
 * This class represents the players playing in the battle arena along with their basic abilities,
 * list of gears and weapon used.
 */
public interface Player {

  /**
   * This method provides the name of the player playing in the arena.
   *
   * @return the name of the player
   */
  public String getName();

  /**
   * This method provides the strength of the player playing in the arena.
   *
   * @return the strength value of the player
   */
  public int getStrength();

  /**
   * This method provides the constitution of the player playing in the arena.
   *
   * @return the constitution value of the player
   */
  public int getConstitution();

  /**
   * This method provides the dexterity of the player playing in the arena.
   *
   * @return the dexterity value of the player
   */
  public int getDexterity();

  /**
   * This method provides the charisma of the player playing in th arena.
   *
   * @return the charisma of the player
   */
  public int getCharisma();

  /**
   * This method provides the bag of all the equipments present with the player in the battle arena.
   *
   * @return the map of the equipment type with the list of all equipments
   */
  public Map<EquipmentType, List<Equipment>> getPlayerBag();

  /**
   * This method adds the equipment to the bag of equipments present with the player.
   *
   * @param equipment this parameter takes the equipment object for a player
   * @throws IllegalArgumentException when equipment passed is null
   */
  public void addEquipment(Equipment equipment) throws IllegalArgumentException;

  /**
   * This method provides the weapon currently present with the player in the arena.
   *
   * @return the current weapon of the player
   */
  public Weapon getCurrentWeapon();

  /**
   * This method sets the current weapon of the player.
   *
   * @param currentWeapon this parameter takes the weapon for a player
   * @throws IllegalArgumentException when weapon passed is null
   */
  public void setCurrentWeapon(Weapon currentWeapon) throws IllegalArgumentException;

  /**
   * This method provides the health of a player playing in the arena.
   *
   * @return the health value of a player
   */
  public int getHealth();

  /**
   * This method provides the striking power of the player playing in the arena.
   *
   * @return the striking power value of a player
   */
  public int getStrikingPower();

  /**
   * This method provides the avoidance ability of the player playing in the arena.
   *
   * @return the avoidance ability value of the player
   */
  public int getAvoidanceAbility();

  /**
   * This method provides the potential damage value of the player playing in th arena.
   *
   * @return the potential damage value of the player
   */
  public int getPotentialDamage();

  /**
   * This method provides the actual damage incurred by a player playing in the arena.
   *
   * @return the actual damage value of the player
   */
  public int getActualDamage();

  /**
   * This method calculates all the powers of the player playing in the battle arena.
   *
   * @param player takes the player object
   * @throws IllegalArgumentException when player object is null
   */
  public void calculatePlayerPowers(Player player) throws IllegalArgumentException;

  /**
   * This method calculates the initial health of the player with the initial abilities
   * strength, charisma, constitution, and the dexterity.
   */
  public void calculateInitialHealth();

  /**
   * This method updated the health of the player after the incurring the damage in the battle.
   *
   * @param reducedHealth takes the reduced health of the player
   */
  public void updateHealth(int reducedHealth);

  /**
   * This method removes all the gears from the equipment bag
   * for which the value is equal to the move.
   *
   * @param move this parameter takes the move number of the battle
   * @return string with details of geared which are removed
   */
  public String removeGears(int move);

}
