package weapon;

import randomizer.RandomGenerator;

public class Flail implements Weapon {

  private final int damage;

  public Flail(RandomGenerator randomGenerator) {
    this.damage = randomGenerator.getNextInt(6,10);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
