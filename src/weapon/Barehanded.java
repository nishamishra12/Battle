package weapon;

/**
 * This class represents the Barehanded weapon which means no weapon
 * and implements all the methods of the interface.
 */
public class Barehanded implements Weapon {

  /**
   * This method gets the damage value of the player playing in a battle arena.
   *
   * @return the damage value of every weapon
   */
  @Override
  public int getDamage() {
    return 0;
  }
}
