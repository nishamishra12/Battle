package weapon;

/**
 * Represents an abstract class for different types of swords.
 */
abstract class Sword implements Weapon {

  private final int damage;

  /**
   * Constructs a sword with damage value of the weapon.
   *
   * @param damage this parameter takes the damage value of a sword type
   */
  public Sword(int damage) {
    this.damage = damage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDamage() {
    return this.damage;
  }
}
