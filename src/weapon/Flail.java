package weapon;

import randomizer.RandomGenerator;

/**
 * This class represents the weapon type Flail and implements all the methods of the interface.
 */
public class Flail implements Weapon {

  private final int damage;

  /**
   * Constructs a class Flail and generates the damage value of the weapon.
   *
   * @param randomGenerator this parameter takes the random generator to calculate the damage value
   */
  public Flail(RandomGenerator randomGenerator) {
    this.damage = randomGenerator.getNextInt(8,12);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDamage() {
    return this.damage;
  }
}
