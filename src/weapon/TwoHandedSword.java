package weapon;

import randomizer.RandomGenerator;

/**
 * This class represents the weapon type two handed sword and extends the abstract class swords.
 */
public class TwoHandedSword extends Sword {

  /**
   * Constructs a class TwoHandedSword which extends the super class constructor
   * and provides the randomly generated damage to the super class.
   *
   * @param randomGenerator this parameter takes the random generator to calculate the damage value
   */
  public TwoHandedSword(RandomGenerator randomGenerator) {
    super(randomGenerator.getNextInt(8, 12));
  }
}
