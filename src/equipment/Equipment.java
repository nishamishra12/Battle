package equipment;

import java.util.List;
import player.Ability;

/**
 * An equipment interface represents all the various types of equipments present in a battle arena
 * that each player can access.
 */
public interface Equipment extends Comparable<Equipment> {

  /**
   * This method provides the effect value of each gear which will affect the ability of the player.
   *
   * @return the effective value of the equipment
   */
  public int getEffectValue();

  /**
   * This method provides list of all the abilities of the player that the gear can affect.
   *
   * @return the list of effecting abilities of the player
   */
  public List<Ability> getEffectAbility();

  /**
   * This method provides the size of each belt in units.
   *
   * @return the unit size of each belt.
   */
  public int getBeltSize();

  /**
   * This method changes the value of the effecting ability to negative.
   */
  public void setEffectValueNegative();

  /**
   * This method provides the equipment type for each gear.
   *
   * @return the type of the equipment
   */
  public EquipmentType getEquipmentType();

  /**
   * This method provides the name of the equipment used by the player.
   *
   * @return the name of the gear
   */
  public String getName();

  /**
   * This method provides the number of moves till which the equipment is temporary.
   *
   * @return the number of moves for each gear
   */
  public int getMove();

}
