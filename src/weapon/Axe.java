package weapon;

import randomizer.RandomGenerator;

public class Axe implements Weapon {

  private final int damage;

  public Axe(RandomGenerator randomGenerator) {
    this.damage = randomGenerator.getNextInt(6,10);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

}
