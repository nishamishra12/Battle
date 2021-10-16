package weapon;

import randomizer.RandomGenerator;

/**
 * This class represents the weapon type katana and extends the abstract class swords.
 */
public class Katana extends Sword {

  /**
   * Constructs a class Katana which extends the super class constructor
   * and provides the randomly generated damage to the super class.
   *
   * @param randomGenerator this parameter takes the random generator to calculate the damage value
   */
  public Katana(RandomGenerator randomGenerator) {
    super(2 * randomGenerator.getNextInt(4, 6));
  }
}
