package weapon;

import randomizer.RandomGenerator;

/**
 * This class represents the weapon type Axe and implements all the methods of the interface.
 */
public class Axe implements Weapon {

  private final int damage;

  /**
   * Constructs a class Axe and generates the damage value of the weapon.
   *
   * @param randomGenerator this parameter takes the random generator to calculate the damage value
   */
  public Axe(RandomGenerator randomGenerator) {
    this.damage = randomGenerator.getNextInt(6, 10);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDamage() {
    return this.damage;
  }

}
