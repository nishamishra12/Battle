package weapon;

import randomizer.RandomGenerator;

/**
 * This class represents the weapon type broadsword and extends the abstract class swords.
 */
public class Broadsword extends Sword {

  /**
   * Constructs a class Broadsword which extends the super class constructor
   * and provides the randomly generated damage to the super class.
   *
   * @param randomGenerator this parameter takes the random generator to calculate the damage value
   */
  public Broadsword(RandomGenerator randomGenerator) {
    super(randomGenerator.getNextInt(6,10));
  }

}
